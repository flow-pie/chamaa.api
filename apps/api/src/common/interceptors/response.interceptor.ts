import {
  Injectable,
  NestInterceptor,
  ExecutionContext,
  CallHandler,
} from '@nestjs/common';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

interface ResponseData {
  success: boolean;
  data: unknown;
}

@Injectable()
export class ResponseInterceptor implements NestInterceptor {
  intercept(
    _context: ExecutionContext,
    next: CallHandler,
  ): Observable<ResponseData> {
    return next.handle().pipe(
      map((data: unknown) => ({
        success: true,
        data,
      })),
    );
  }
}
