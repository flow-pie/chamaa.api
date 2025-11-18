// wallet.service.ts
import { Injectable } from '@nestjs/common';

@Injectable()
export class WalletService {
  getWallets() {
    return [{ id: 1, balance: 1000 }];
  }
}
