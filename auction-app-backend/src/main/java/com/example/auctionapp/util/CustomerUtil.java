package com.example.auctionapp.util;

import com.stripe.exception.StripeException;
import com.stripe.model.Customer;
import com.stripe.model.CustomerSearchResult;
import com.stripe.param.CustomerCreateParams;
import com.stripe.param.CustomerSearchParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CustomerUtil {
    private static final Logger logger = LoggerFactory.getLogger(CustomerUtil.class);

    public static Customer findOrCreateCustomer(final String email, final String name) throws StripeException {
        logger.info("Searching or creating customer for email: {}", email);

        final CustomerSearchParams params =
                CustomerSearchParams
                        .builder()
                        .setQuery("email:'" + email + "'")
                        .build();

        final CustomerSearchResult result = Customer.search(params);

        Customer customer;

        // if no customer was found, create new one
        if (result.getData().isEmpty()) {
            logger.info("No customer found, creating new one for email: {}", email);
            final CustomerCreateParams customerCreateParams = CustomerCreateParams.builder()
                    .setName(name)
                    .setEmail(email)
                    .build();

            customer = Customer.create(customerCreateParams);

            logger.info("New customer created with ID: {}", customer.getId());
        } else {
            customer = result.getData().get(0);

            logger.info("Customer retrieved with ID: {}", customer.getId());
        }

        return customer;
    }
}
