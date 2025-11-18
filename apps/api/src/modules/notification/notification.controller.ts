// notification.controller.ts
import { Controller, Get } from '@nestjs/common';
import { NotificationService } from './notification.service';

@Controller('notifications')
export class NotificationController {
  constructor(private service: NotificationService) {}

  @Get()
  all() {
    return this.service.all();
  }
}
