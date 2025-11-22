import { Injectable } from '@nestjs/common';

@Injectable()
export class AuthService {
  login(email: string) {
    return { token: 'sample-token', email };
  }
}
