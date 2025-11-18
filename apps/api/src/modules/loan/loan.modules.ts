// loan.module.ts
import { Module } from '@nestjs/common';
import { LoanController } from './loan.controller';
import { LoanService } from './loan.service';

@Module({
  controllers: [LoanController],
  providers: [LoanService],
})
export class LoanModule {}
