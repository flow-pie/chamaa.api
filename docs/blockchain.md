# Blockchain Integration

## Overview

Chamaa.API integrates with the Polygon blockchain to provide transparent, immutable transaction records for loan agreements and payments.

## Why Polygon?

- Low transaction fees compared to Ethereum mainnet
- Fast confirmation times
- EVM-compatible for easy integration
- Testnet (Amoy) available for development

## Architecture

```
┌─────────────────┐     ┌──────────────────┐     ┌─────────────┐
│  LoanService    │────>│  ContractService │────>│  Polygon    │
│  (Business)     │     │  (Contract mgmt) │     │  (Network)  │
└─────────────────┘     └──────────────────┘     └─────────────┘
                               │
                               ▼
                        ┌─────────────┐
                        │  Web3j      │
                        │  (SDK)      │
                        └─────────────┘
```

## Services

### BlockchainService

Low-level blockchain operations:

```java
public interface BlockchainService {
    String submitTransaction(String from, String to, BigDecimal amount);
    BigDecimal getBalance(String walletAddress);
    String deployContract(String abi, String bytecode, List<String> params);
    String callContractFunction(String contractAddress, String function, List<Object> params);
}
```

### ContractService

Manages loan-specific smart contracts:

```java
public interface ContractService {
    String deployLoanContract(Group group, BigDecimal amount, int durationMonths);
    String recordLoanPayment(String contractAddress, BigDecimal amount);
    BigDecimal getLoanBalance(String contractAddress);
    boolean verifyPayment(String txHash);
}
```

### PolygonService

Polygon network-specific operations:

```java
public interface PolygonService {
    NetworkStatus getNetworkStatus();
    BigDecimal estimateGasFee(String txData);
    String submitTransaction(TransactionData txData);
    String getTransactionReceipt(String txHash);
}
```

## Configuration

Add to `application.properties`:

```properties
# Polygon Configuration
blockchain.polygon.rpc-url=https://polygon-rpc.com
blockchain.polygon.chain-id=137
blockchain.polygon.private-key=${POLYGON_PRIVATE_KEY}

# For testnet
blockchain.polygon.rpc-url=https://rpc-amoy.polygon.technology
blockchain.polygon.chain-id=80001
```

## Integration Points

### Loan Creation Flow

```
1. User requests loan → LoanController
2. LoanService validates and creates Loan entity
3. ContractService deploys loan smart contract
4. BlockchainService submits deployment transaction
5. Contract address stored with Loan record
6. Response returned to client
```

### Payment Processing

```
1. Payment initiated → WalletService
2. Transaction recorded in database
3. BlockchainService validates/records on-chain
4. ContractService updates loan status
5. Both DB and blockchain in sync
```

## Smart Contract (Future)

Planned smart contract features:

```solidity
// Planned: Loan Agreement Contract
contract LoanAgreement {
    address borrower;
    address lender;
    uint256 amount;
    uint256 duration;
    uint256 installmentsPaid;
    
    function makePayment() external;
    function calculatePenalty() public view returns (uint256);
    function getRemainingBalance() public view returns (uint256);
}
```

## Development

### Setting Up

1. Create Polygon wallet
2. Get MATIC tokens (testnet for development)
3. Configure private key in environment
4. Use Amoy testnet for development

### Testing

```bash
# Deploy to testnet
./mvnw spring-boot:run -Dspring-boot.run.arguments=--blockchain.polygon.rpc-url=https://rpc-amoy.polygon.technology
```

## Future Enhancements

- [ ] Smart contract for automatic loan disbursement
- [ ] On-chain voting for loan approval
- [ ] Automated penalty enforcement
- [ ] Event listeners for real-time updates
- [ ] Transaction fee optimization
- [ ] Multi-signature wallet support

## Security Considerations

- Private keys stored as environment variables
- Transaction signing happens server-side
- Non-custodial - user funds stay in their wallets
- Audit trails both on-chain and in database
