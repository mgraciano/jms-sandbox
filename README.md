# jms-sandbox
Sandbox for some JMS and Camel studies

# Some errors and possible solutions

## No component found with scheme: jms
Need to specify the correct dependency in the POM.

## java.lang.IllegalArgumentException: connectionFactory must be specified
Needed to declare a ConnectionFactory resource and configure the rote builder correctly. See MyRoutes for more details.
