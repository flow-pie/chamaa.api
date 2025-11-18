// notification.service.ts
import { Injectable } from '@nestjs/common';

@Injectable()
export class NotificationService {
  all() {
    return [{ id: 1, message: 'Welcome to Chamaa Chain' }];
  }
}
