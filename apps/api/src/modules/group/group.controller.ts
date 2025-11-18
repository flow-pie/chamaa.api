import { Controller, Get } from '@nestjs/common';
import { GroupService } from './group.service';

@Controller('groups')
export class GroupController {
  constructor(private service: GroupService) {}

  @Get()
  list() {
    return this.service.list();
  }
}
