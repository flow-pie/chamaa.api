import { Injectable } from '@nestjs/common';

@Injectable()
export class AuthService {
  async login(email: string) {
    return { token: 'sample-token', email };
  }
}
