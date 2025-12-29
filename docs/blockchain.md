# Blockchain Integration

## Overview
Chamaa API integrates with the Polygon blockchain for:
- Smart contract deployment
- Transaction recording
- Loan agreement execution
- Payment verification

## Services

### BlockchainService
Handles low-level blockchain operations:
- `submitTransaction(from, to, amount)` - Submit blockchain transaction
- `getBalance(walletAddress)` - Get wallet balance
- `deployContract(code)` - Deploy smart contract
- `callContractFunction(address, function, params)` - Call contract function

### ContractService
Manages loan-specific smart contracts:
- `deployLoanContract(group, amount, duration)` - Deploy loan contract
- `recordLoanPayment(contract, amount)` - Record payment
- `getLoanBalance(contract)` - Get loan balance

### PolygonService
Polygon network specific operations:
- `getNetworkStatus()` - Check network status
- `estimateGasFee()` - Estimate transaction fees
- `submitTransactionToPolygon(txData)` - Submit to Polygon

## Configuration

Add to `application.properties`:
```properties
blockchain.polygon.rpc-url=https://polygon-rpc.com
blockchain.polygon.chain-id=137
```

## Integration Points

### Loan Creation Flow
1. User requests loan → LoanController
2. LoanService creates Loan entity
3. ContractService deploys smart contract
4. BlockchainService records on chain
5. Transaction recorded in database

### Payment Processing
1. Payment initiated → WalletService
2. BlockchainService validates balance
3. ContractService records payment
4. Transaction status updated in DB

## Future Enhancements
- Implement smart contract ABI parsing
- Add transaction fee optimization
- Implement async transaction polling
- Add blockchain event listeners
- Integrate with Web3j properly
