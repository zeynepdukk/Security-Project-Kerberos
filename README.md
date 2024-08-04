
# Security-Project-Kerberos
This project is a Java-based system designed to handle user authentication, ticket generation, and key management. It includes components for an authentication server, a key update interface, and a time server/client system.
## Components
### AuthTicketServer
Handles user authentication.
Issues tickets to authenticated users.
Maintains a list of user credentials and client tickets.

### KeyUpdateInterface
Provides an interface to update server and client keys.
Simulates the generation of RSA key pairs for secure communications.

### TimeServer
A concurrent server that responds with the current time.
Utilizes multi-threading to handle multiple client requests simultaneously

### TimeClient
Connects to the TimeServer and prints the server's response.
Demonstrates concurrent client connections
