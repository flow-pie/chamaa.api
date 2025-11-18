import dotenv from 'dotenv';
import { ethers } from 'ethers';
dotenv.config();

const rpc = process.env.BLOCKCHAIN_RPC_URL || 'https://rpc-mumbai.maticvigil.com';
const provider = new ethers.JsonRpcProvider(rpc);

async function test() {
  const block = await provider.getBlockNumber();
  // eslint-disable-next-line no-console
  console.log('Connected to chain, block:', block);
}

test().catch((e) => {
  // eslint-disable-next-line no-console
  console.error('Chain connection failed', e);
  process.exit(1);
});
