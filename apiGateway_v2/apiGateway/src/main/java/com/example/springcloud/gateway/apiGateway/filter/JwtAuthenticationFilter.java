package com.example.springcloud.gateway.apiGateway.filter;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.example.springcloud.gateway.apiGateway.exception.JwtTokenMalformedException;
import com.example.springcloud.gateway.apiGateway.exception.JwtTokenMissingException;
import com.example.springcloud.gateway.apiGateway.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import io.jsonwebtoken.Claims;
import reactor.core.publisher.Mono;

@Component
public class JwtAuthenticationFilter implements GatewayFilter {

    @Autowired
    private JwtUtil jwtUtil;

    private ArrayList<String> stringArrayList;

    private String urlToCheck = "";

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = (ServerHttpRequest) exchange.getRequest();

        final List<String> apiEndpoints = List.of("/api/auth/register", "/api/auth/auth", "/api/auth/userdetails");

        Predicate<ServerHttpRequest> isApiSecured = r -> apiEndpoints.stream()
                .noneMatch(uri -> r.getURI().getPath().contains(uri));

        if (isApiSecured.test(request)) {
            if (!request.getHeaders().containsKey("Authorization")) {
                ServerHttpResponse response = exchange.getResponse();
                response.setStatusCode(HttpStatus.UNAUTHORIZED);
                return response.setComplete();
            }

            final String token = request.getHeaders().getOrEmpty("Authorization").get(0).substring(7);

            try {
                jwtUtil.validateToken(token);
            } catch (JwtTokenMalformedException | JwtTokenMissingException e) {
                // e.printStackTrace();

                ServerHttpResponse response = exchange.getResponse();
                response.setStatusCode(HttpStatus.BAD_REQUEST);

                return response.setComplete();
            }

            urlToCheck = request.getURI().toString();
            String numberToPass = "";
            for (int i = 21; i < urlToCheck.length(); i++) {
                if (urlToCheck.charAt(i) >= 48 && urlToCheck.charAt(i) <= 57) {
                    numberToPass += urlToCheck.charAt(i);
                    if (i < urlToCheck.length() - 1) {
                        if (urlToCheck.charAt(i + 1) < 48 || urlToCheck.charAt(i + 1) > 57) {
                            numberToPass += ",";
                        }
                    }
                }
            }

            //ArrayList contains each parameter passed in, in a form of numbers
            stringArrayList = new ArrayList<>(List.of(numberToPass.split(",")));
            if (stringArrayList.size() < 3) {
                stringArrayList.add("1");
                stringArrayList.add("1");
                stringArrayList.add("1");

            }

            Claims claims = jwtUtil.getClaims(token);
            final List<String> apiEndpointsEmployee = List.of(

                    // expose "/users"
                    "/api/auth/users",

                    //prints out last 30 transactions of all bank accounts
                    "/api/transaction/all_operations",

                    //prints out all bank accounts associated with a certain user
                    "/api/account/users/" + stringArrayList.get(0) + "/all_accounts",

                    //prints out all requests made by users, so that the employee can accept or deny them
                    "/api/account/accounts/requests",

                    // inactive a specific account passing in its id(theId)
                    "/api/account/accounts/inactivation/" + stringArrayList.get(0),

                    //Activate a specific account passing in its id(theId)
                    "/api/account/accounts/activation/" + stringArrayList.get(0));

            final List<String> apiEndpointsClient = List.of(

                    //GET a user with its id /users/{userId}
                    "/api/auth/users/" + stringArrayList.get(0),

                    //add mapping for PUT /users - update existing users
                    "/api/auth/users/update/email", "/api/auth/users/update/password",

                    //making a deposit(passing in a positive integer), withdrawal(passing in a negative integer)
                    "/api/transaction/make/transaction",

                    //Gets all transactions having the prelievo operation type,
                    "/api/transaction/filter/prelievo/" + stringArrayList.get(0),

                    //Gets last_ten transactions having the prelievo operation type,
                    "/api/transaction/filter/prelievo/" + stringArrayList.get(0) + "/last_ten",

                    //Gets last_twenty transactions having the prelievo operation type,
                    "/api/transaction/filter/prelievo/" + stringArrayList.get(0) + "/last_twenty",

                    //Gets last_fifty transactions having the prelievo operation type,
                    "/api/transaction/filter/prelievo/" + stringArrayList.get(0) + "/last_fifty",

                    //Gets all transactions having the deposito operation type,
                    "/api/transaction/filter/deposito/" + stringArrayList.get(0),

                    //Gets last_ten transactions having the deposito operation type,
                    "/api/transaction/filter/deposito/" + stringArrayList.get(0) + "/last_ten",

                    //Gets last_twenty transactions having the deposito operation type,
                    "/api/transaction/filter/deposito/" + stringArrayList.get(0) + "/last_twenty",

                    //Gets last_fifty transactions having the deposito operation type,
                    "/api/transaction/filter/deposito/" + stringArrayList.get(0) + "/last_fifty",

                    //Gets all transactions having the bonifico operation type,
                    "/api/transaction/filter/bonifico/" + stringArrayList.get(0),

                    //Gets last_ten transactions having the bonifico operation type,
                    "/api/transaction/filter/bonifico/" + stringArrayList.get(0) + "/last_ten",

                    //Gets last_twenty transactions having the bonifico operation type,
                    "/api/transaction/filter/bonifico/" + stringArrayList.get(0) + "/last_twenty",

                    //Gets last_fifty transactions having the bonifico operation type,
                    "/api/transaction/filter/bonifico/" + stringArrayList.get(0) + "/last_fifty",

                    //Gets all transactions having the ricarica_telefonica operation type,
                    "/api/transaction/filter/ricarica_telefonica/" + stringArrayList.get(0),

                    //Gets last_ten transactions having the ricarica_telefonica operation type,
                    "/api/transaction/filter/ricarica_telefonica/" + stringArrayList.get(0) + "/last_ten",

                    //Gets last_twenty transactions having the ricarica_telefonica operation type,
                    "/api/transaction/filter/ricarica_telefonica/" + stringArrayList.get(0) + "/last_twenty",

                    //Gets last_fifty transactions having the ricarica_telefonica operation type,
                    "/api/transaction/filter/ricarica_telefonica/" + stringArrayList.get(0) + "/last_fifty",

                    //Making a transfer(bonifico) from one existing account in the db to another existing account
                    "/api/transaction/transfer",

                    //Recharging the user's phone credit(top up)
                    "/api/transaction/ricarica_telefonica",

                    //All transactions for a specific bank account,
                    "/api/transaction/" + stringArrayList.get(0) + "/transactions",

                    //prints out last 10 transactions of a certain bank account
                    "/api/transaction/last_ten_operation/" + stringArrayList.get(0),

                    //prints out last 20 transactions of a certain bank account
                    "/api/transaction/last_twenty_operation/" + stringArrayList.get(0),

                    //prints out last 50 transactions of a certain bank account
                    "/api/transaction/last_fifty_operation/" + stringArrayList.get(0),

                    //prints out all bank accounts associated with a certain user
                    "/api/account/users/" + stringArrayList.get(0) + "/accounts",

                    //gets the account by its id
                    "/api/account/" + stringArrayList.get(0),

                    //Requesting a closure for the bank account by the user, passing in the account's id(theId)
                    "/api/account/accounts/closure_request/" + stringArrayList.get(0),

                    //Requesting an activation for the bank account by the user, passing in the account's id(theId)
                    "/api/account/accounts/activation_request/" + stringArrayList.get(0),

                    //Open a new account providing an account's number of the same user,
                    // and an initial amount from the old account into the new one
                    "/api/account/" + stringArrayList.get(0) + "/addAccount/" + stringArrayList.get(1) + "/" + stringArrayList.get(2));

            isApiSecured = r -> apiEndpointsClient.stream()
                    .anyMatch(uri -> r.getURI().getPath().contains(uri));

            //throughout a loop a check is made to deduce that the URI exists in an endPoint permitted for the client
            for (String s : apiEndpointsClient) {
                String control = "http://localhost:8080" + s;
                if (control.equals(urlToCheck)) {
                    if (isApiSecured.test(request)) {
                        if (!((ArrayList<String>) claims.get("authorities")).contains("ROLE_CLIENT")) {
                            ServerHttpResponse response = exchange.getResponse();
                            response.setStatusCode(HttpStatus.UNAUTHORIZED);
                            return response.setComplete();
                        }
                    }
                    exchange.getRequest().mutate().header("id", String.valueOf(claims.get("id"))).build();
                    break;
                }
            }

            isApiSecured = r -> apiEndpointsEmployee.stream()
                    .anyMatch(uri -> r.getURI().getPath().contains(uri));

            //throughout a loop a check is made to deduce that the URI exists in an endPoint permitted for the employee
            for (String s : apiEndpointsEmployee) {
                String control = "http://localhost:8080" + s;
                if (control.equals(urlToCheck)) {
                    if (isApiSecured.test(request)) {
                        if (!((ArrayList<String>) claims.get("authorities")).contains("ROLE_EMPLOYEE")) {
                            ServerHttpResponse response = exchange.getResponse();
                            response.setStatusCode(HttpStatus.UNAUTHORIZED);
                            return response.setComplete();
                        }
                    }
                    exchange.getRequest().mutate().header("id", String.valueOf(claims.get("id"))).build();
                    break;
                }
            }

        }

        return chain.filter(exchange);
    }

}