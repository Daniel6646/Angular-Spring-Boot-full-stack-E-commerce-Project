package com.luv2code.ecommerce.service;

import com.luv2code.ecommerce.dto.PaymentInfo;
import com.luv2code.ecommerce.dto.Purchase;

import com.luv2code.ecommerce.dto.PurchaseResponse;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;

public interface CheckOutService {

	PurchaseResponse placeOrder(Purchase purchase);
	// send purchase dto send back purchase response

	// stripe api for purchase & payment
	PaymentIntent createPaymentIntent(PaymentInfo paymentInfo) throws StripeException;
//A payment intent in Stripe is a way to collect payments from your customers. It is a more modern way of handling payments than the traditional charge API. Payment intents allow you to:
//Collect payments from multiple payment methods in a single flow.
//Support Strong Customer Authentication (SCA) and other regulatory requirements.
//Track the status of a payment throughout the checkout process.
//Create a more seamless checkout experience for your customers.
//To create a payment intent, you will need to specify the following information:
//The amount of the payment.
//The currency of the payment.
//The customer's payment information.
//The desired outcome of the payment (e.g., capture the payment immediately, authorize the payment and capture it later).
//Once you have created a payment intent, you can use the Stripe API to interact with it. For example, you can:
//Confirm the payment.
//Cancel the payment.
//Refund the payment.

}
