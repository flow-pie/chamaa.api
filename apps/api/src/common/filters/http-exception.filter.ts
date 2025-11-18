import {
  ExceptionFilter,
  Catch,
  ArgumentsHost,
  HttpException,
} from '@nestjs/common';

@Catch()
export class HttpExceptionFilter implements ExceptionFilter {
  catch(exception: any, host: ArgumentsHost) {
    const ctx = host.switchToHttp();
    const res = ctx.getResponse();
    const status =
      exception instanceof HttpException ? exception.getStatus() : 500;

    res.status(status).json({
      success: false,
      statusCode: status,
      message: exception.message || 'Internal server error',
    });
  }
}
