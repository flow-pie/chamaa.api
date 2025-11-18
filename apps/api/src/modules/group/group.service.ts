import { Injectable } from '@nestjs/common';

@Injectable()
export class GroupService {
    list() {
        return [{ id: 1, name: 'Savings Group' }];
    }
}
