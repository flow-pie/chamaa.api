import {
  ExceptionFilter,
  Catch,
  ArgumentsHost,
  HttpException,
} from '@nestjs/common';

interface HttpExceptionResponse {
  message?: string;
}

@Catch()
export class HttpExceptionFilter implements ExceptionFilter {
  catch(exception: unknown, host: ArgumentsHost): void {
    const ctx = host.switchToHttp();
    const res = ctx.getResponse();
    const status =
      exception instanceof HttpException ? exception.getStatus() : 500;
    
    const message =
      exception instanceof HttpException
        ? (exception.getResponse() as HttpExceptionResponse).message || exception.message
        : 'Internal server error';

    res.status(status).json({
      success: false,
      statusCode: status,
      message,
    });
  }
}
