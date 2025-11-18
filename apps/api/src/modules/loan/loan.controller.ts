// loan.controller.ts
import { Controller, Get } from '@nestjs/common';
import { LoanService } from './loan.service';

@Controller('loans')
export class LoanController {
  constructor(private service: LoanService) {}

  @Get()
  all() {
    return this.service.all();
  }
}
