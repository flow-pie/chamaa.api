// transaction.controller.ts
import { Controller, Get } from '@nestjs/common';
import { TransactionService } from './transaction.service';

@Controller('transactions')
export class TransactionController {
  constructor(private service: TransactionService) {}

  @Get()
  getAll() {
    return this.service.getAll();
  }
}
