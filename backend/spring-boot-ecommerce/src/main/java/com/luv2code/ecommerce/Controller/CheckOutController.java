package com.luv2code.ecommerce.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luv2code.ecommerce.dto.PaymentInfo;
import com.luv2code.ecommerce.dto.Purchase;
import com.luv2code.ecommerce.dto.PurchaseResponse;
import com.luv2code.ecommerce.service.CheckOutService;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;

//@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/api/checkout")
public class CheckOutController {

	@Autowired
	CheckOutService checkOutService;
	
	
	@PostMapping("/purchase")
	public PurchaseResponse placeOrder(@RequestBody Purchase purchase) {
		
		PurchaseResponse response =  checkOutService.placeOrder(purchase);
		
		return response;
	}
	
	
	  @PostMapping("/payment-intent")
	    public ResponseEntity<String> createPaymentIntent(@RequestBody PaymentInfo paymentInfo) throws StripeException {

	        PaymentIntent paymentIntent = checkOutService.createPaymentIntent(paymentInfo);

	        String paymentStr = paymentIntent.toJson();

	        return new ResponseEntity<>(paymentStr, HttpStatus.OK);
	    }
	
	
}
