# Flight Search with Redis Cache

This is a flight search application built with Spring Boot that utilizes Redis caching to improve performance and reduce API calls to external flight data providers.

## Features

- **Search Flights**: Allows users to search for flights based on departure, destination, and date.
- **Caching**: Utilizes Redis caching to store search results and improve response times for repeated searches.
- **Guest User Tracking**: Tracks guest users with unique identifiers stored in cookies for personalized experiences.

## Prerequisites

Before running the application, ensure you have the following installed:

- Spring boot v3.2.3
- Java Development Kit (JDK) 11 or higher
- Maven
- Redis Server

## Getting Started

1. Clone the repository:

    ```bash
    git clone https://github.com/ahmedemad3/flightsearchservice.git
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

Ensure that Redis server is running locally or using docker or update the Redis configuration in `application.properties` if using a remote server.

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

1. Open your web browser and navigate to the flight search application.
2. Enter the departure airport, destination airport, and desired travel date.
3. Click on the search button to retrieve available flights.
4. Results will be displayed, and subsequent searches for the same criteria will be cached for improved performance.

## Contributing

Contributions are welcome! If you find any issues or have suggestions for improvements, feel free to open an issue or submit a pull request.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

---

Feel free to customize this README according to your specific project details and requirements.
