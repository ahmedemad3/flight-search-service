# Flight Search with Redis Cache

This is a flight search application built with Spring Boot that utilizes Redis caching to improve performance and reduce API calls to external flight data providers.

## Features

- **Search Flights**: Allows users to search for flights based on departure, destination, and date.
- **Caching**: Utilizes Redis caching to store search results and improve response times for repeated searches.
- **Guest User Tracking**: Tracks guest users with unique identifiers stored in cookies for personalized experiences.

## Prerequisites

Before running the application, ensure you have the following installed:

- Springboot V3.2.3
- Java Development Kit (JDK) 17 or higher
- Maven
- Redis Server

## Getting Started

1. Clone the repository:

    ```bash
    git clone https://github.com/ahmedemad3/flight-search-service.git
    ```

2. Navigate to the project directory:

    ```bash
    cd flight-search
    ```

3. Build the project using Maven:

    ```bash
    mvn clean install
    ```

4. Run the application:

    ```bash
    mvn spring-boot:run
    ```

5. Access the application in your web browser at `http://localhost:8080`.

## Configuration

### Redis Setup

Ensure that Redis server is running locally or update the Redis configuration in `application.properties` if using a remote server.

```properties
# Redis Configuration
spring.redis.host=localhost
spring.redis.port=6379
```

### Cache Expiration

You can configure the expiration time for cached flight search results in `RedisConfig.java`:

```java
@Bean
public RedisCacheConfiguration cacheConfiguration() {
    return RedisCacheConfiguration.defaultCacheConfig()
        .entryTtl(Duration.ofMinutes(60)) // Cache entries expire after 60 minutes
        .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer()));
}
```

## Usage

### API Endpoint

You can search for flights using the following API endpoint:

**GET /api/v1/flight/search**

Query Parameters:
- `to`: Destination airport code (e.g., "LHE")
- `from`: Departure airport code (e.g., "DXB")
- `departDate`: Departure date in YYYY-MM-DD format (e.g., "2015-03-31")
- `returnDate`: Return date in YYYY-MM-DD format (optional)
- `numberOfTravellers`: Number of travelers (optional)

Example API Call:

```
GET http://localhost:8080/api/v1/flight/search?to="LHE"&from="DXB"&departDate="2015-03-31"&returnDate="2015-04-07"&numberOfTravellers=3
```

## Contributing

Contributions are welcome! If you find any issues or have suggestions for improvements, feel free to open an issue or submit a pull request.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
