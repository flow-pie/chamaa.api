// transaction.service.ts
import { Injectable } from '@nestjs/common';

@Injectable()
export class TransactionService {
    getAll() {
        return [{ id: 1, type: 'deposit', amount: 500 }];
    }
}
