package com.luv2code.ecommerce.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.luv2code.ecommerce.dao.CustomerRepository;
import com.luv2code.ecommerce.dto.PaymentInfo;
import com.luv2code.ecommerce.dto.Purchase;
import com.luv2code.ecommerce.dto.PurchaseResponse;
import com.luv2code.ecommerce.entity.Customer;
import com.luv2code.ecommerce.entity.Order;
import com.luv2code.ecommerce.entity.OrderItem;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;

import jakarta.transaction.Transactional;

@Service
public class CheckOutServiceImpl implements CheckOutService {

	// @Autowired /constructor injection like below is the same 
	CustomerRepository customerRepository;

	PurchaseResponse purchaseResponse;

	// can also do injection using a constructor

	public CheckOutServiceImpl(CustomerRepository customerRepository,
							    @Value("${stripe.key.secret}") String secretKey) {

		this.customerRepository = customerRepository;

		// initialize Stripe API with secret key
		Stripe.apiKey = secretKey;
	}

	@Value("${stripe.key.secret}")
	String secretKey;

	@Override
	@Transactional
	public PurchaseResponse placeOrder(Purchase purchase) {

		// retrieve order info from dto
		Order order = purchase.getOrder();

		// generate tracking number
		String orderTrackingNumber = generateOrderTrackingNumber();
		order.setOrderTrackingNumber(orderTrackingNumber);

		// populate orders with order items
		Set<OrderItem> orderItems = purchase.getOrderItems();
		orderItems.forEach(items -> order.addOrder(items));

		// populate order address with billingaddress and shippingaddress
		order.setBillingAddress(purchase.getBillingAddress());
		order.setShippingAddress(purchase.getShippingAddress());

		// populate customer with order
		Customer customer = purchase.getCustomer();
		customer.add(order);

		// for unique email
		// check if customer is present
		String theEmail = customer.getEmail();

		// see if customer exists in db START
		Customer customerFromDb = customerRepository.findByEmail(theEmail);

		if (customerFromDb != null) {
			// we found data assign accrdngly
			// if available use same customer or new will be saved
			customer = customerFromDb;
		}
		// see if customer exists in db END

		customer.add(order);

		// save the order
		customerRepository.save(customer);

		// return a response
		purchaseResponse = new PurchaseResponse(orderTrackingNumber);

		return purchaseResponse;
	}

	private String generateOrderTrackingNumber() {

		// create a unique id that is hard to guess and unique

		// generate a random UUID number
		// What is UUID.? uNIVERSAL UNIQUE IDENTIFIER
		// METHOD FOR GENERATING UNIQUE Ids

		// generate a random number
		System.out.println("Random number from UUID::::" + UUID.randomUUID().toString());
		return UUID.randomUUID().toString();
	}

	@Override
	public PaymentIntent createPaymentIntent(PaymentInfo paymentInfo) throws StripeException {

		 List<String> paymentMethodTypes = new ArrayList<>();
	        paymentMethodTypes.add("card");

	        Map<String, Object> params = new HashMap<>();
	        params.put("amount", paymentInfo.getAmount());
	        params.put("currency", paymentInfo.getCurrency());
	        params.put("payment_method_types", paymentMethodTypes);
	        params.put("description", "Luv2Shop Purchase ");
	        params.put("receipt_email", paymentInfo.getReceiptEmail());
	        //params.put("3d_secure", false);
	        return PaymentIntent.create(params);
	        
	}

}
