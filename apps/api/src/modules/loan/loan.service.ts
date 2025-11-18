// loan.service.ts
import { Injectable } from '@nestjs/common';

@Injectable()
export class LoanService {
  all() {
    return [{ id: 1, amount: 2000, status: 'pending' }];
  }
}
