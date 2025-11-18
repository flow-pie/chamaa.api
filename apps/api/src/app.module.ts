import { Module } from '@nestjs/common';
import { ConfigModule } from './config/config.module';
import { AuthModule } from './modules/auth/auth.module';
import { UserModule } from './modules/user/user.module';
import { GroupModule } from './modules/group/group.module';
import { TransactionModule } from './modules/transaction/transaction.module';
import { LoanModule } from './modules/loan/loan.module';
import { WalletModule } from './modules/wallet/wallet.module';
import { NotificationModule } from './modules/notification/notification.module';

@Module({
  imports: [
    ConfigModule,
    AuthModule,
    UserModule,
    GroupModule,
    TransactionModule,
    LoanModule,
    WalletModule,
    NotificationModule,
  ],
})
export class AppModule {}
