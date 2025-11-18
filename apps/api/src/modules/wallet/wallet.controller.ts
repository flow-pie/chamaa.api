// wallet.controller.ts
import { Controller, Get } from '@nestjs/common';
import { WalletService } from './wallet.service';

@Controller('wallets')
export class WalletController {
  constructor(private service: WalletService) {}

  @Get()
  getWallets() {
    return this.service.getWallets();
  }
}
