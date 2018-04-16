package sizeyunsuan;

import java.math.BigDecimal;

import java.text.DecimalFormat;
import java.util.Random;
import java.util.Scanner;

public class SiZeYunSuan {
	private static int rightNum = 0;// 正确的个数
    public static void main(String[] args) {
		// TODO Auto-generated method stub
    	makeQuestion();
	 }
    public static void showResult(int questionNum) {
			if (rightNum == questionNum) {
			System.out.println("非常 不错，全对！我已经没什么可以教你的了。");
			} else {
			DecimalFormat df = new DecimalFormat("0.00%");// 格式化输出
			String rightRateStr;// 存储比率的字符串
			double rightRate;// 正确率
			rightRate = (double) rightNum / (double) questionNum;
			rightRateStr = df.format(rightRate);
			System.out.println("正确率为：" + rightRateStr);
			System.out.println("革命尚未成功，同志仍需努力！");
			}
			}
	public static void makeQuestion() {
		Scanner in=new Scanner(System.in);
		int operation=-1;
		int MaxNum=-1;
    	int questionNum=-1;
    	System.out.println("请选择题目数量：");
    	questionNum=in.nextInt();
    	System.out.println("请选择运算符：");
    	operation=choseope();
    	System.out.println("请设置最大数：");
    	MaxNum=in.nextInt();
    	System.out.println("请选择是否带有小数：");
    	String Decimal=in.next();
		char Dec=Decimal.charAt(0);
    	//in.close();
    	if(Dec=='是'){
    		DecimalAc(operation,questionNum,MaxNum);
    	}
    	else {
    		IntAc(operation,questionNum,MaxNum);
    	}
			}
	public static void IntAc(int operation,int questionNum,int MaxNum) {
		int inputResult = -1;// 输入的答案
		int num = -1;// 标记运算符
		int num1 = -1;// 运算数1
		int num2 = -1;// 运算数2
		int rightResult = -1;// 正确的运算结果
		Random random = new Random();
		for (int i = 1; i <= questionNum; i++) {
			num1 = random.nextInt(MaxNum);// 随机生成在[0,MaxNum]范围内的运算数1
			num2 = random.nextInt(MaxNum);// 随机生成在[0,MaxNum]范围内的运算数2
			if(operation==4) {
			num = random.nextInt(3);// 产生随机数，代表运算符0为+，1为-，2为*，3为/
			switch (num) {
			case 0: {
			rightResult = num1 + num2;// 计算结果
			System.out.print(num1 + "+" + num2 + "=");// 输出题目
			break;
			}
			case 1: {
			rightResult = num1 - num2;
			System.out.print(num1 + "-" + num2 + "=");
			break;
			}
			case 2: {
			rightResult = num1 * num2;
			System.out.print(num1 + "*" + num2 + "=");
			break;
			}
			case 3: {
			if (num2 == 0) {
			num2 = num2 + 1;
			}
			rightResult = num1 / num2;
			System.out.print(num1 + "/" + num2 + "=");
			break;
			}
			}
			}
			else if(operation==0){
				rightResult = num1 + num2;// 计算结果
				System.out.print(num1 + "+" + num2 + "=");// 输出题目
				}
			else if(operation==1){
				rightResult = num1 - num2;
				System.out.print(num1 + "-" + num2 + "=");
				}
			else if(operation==2){
				rightResult = num1 * num2;
				System.out.print(num1 + "*" + num2 + "=");
				}
			else {
				if (num2 == 0) {
				num2 = num2 + 1;
				}
				 rightResult = num1 / num2;
				System.out.print(num1 + "/" + num2 + "=");
			   }
			inputResult = inputResult();
			if (rightResult == inputResult) {
			rightNum += 1;
			System.out.print("\t正确");
			System.out.println();
			} else {
			System.out.print("\t错误,正确答案为：" + rightResult);
			System.out.println();
			}
		}
		showResult(questionNum);
	}
	public static void DecimalAc(int operation,int questionNum,int MaxNum) {
		double inputResult=-1.11;
		double num1=-1.11;
		double num2=-1.11;
		double rightResult=-1.11;
		int num=-1;
		Random random = new Random();
		for (int i = 1; i <= questionNum; i++) {
			num1=random.nextDouble()*MaxNum;
			num1= (double) Math.round(num1 * 100) / 100;
			BigDecimal bnum1 = new BigDecimal(Double.toString(num1));
			num2=random.nextDouble()*MaxNum;
			num2= (double) Math.round(num2 * 100) / 100;
			BigDecimal bnum2 = new BigDecimal(Double.toString(num2));
			if(operation==4) {
			num = random.nextInt(3);// 产生随机数，代表运算符0为+，1为-，2为*，3为/
			switch (num) {
			case 0: {
			rightResult = bnum1.add(bnum2).doubleValue();;// 计算结果
			System.out.print(bnum1 + "+" + bnum2 + "=");// 输出题目
			break;
			}
			case 1: {
			rightResult = bnum1.subtract(bnum2).doubleValue();
			System.out.print(bnum1 + "-" + bnum2 + "=");
			break;
			}
			case 2: {
			rightResult = bnum1.multiply(bnum2).doubleValue();
			System.out.print(bnum1 + "*" + bnum2 + "=");
			break;
			}
			case 3: {
			if (num2 == 0) {
			num2 = num2 + 1;
			}
			rightResult = num1 / num2;
			System.out.print(num1 + "/" + num2 + "=");
			break;
			}
			}
			}
			else if(operation==0){
				rightResult = bnum1.add(bnum2).doubleValue();// 计算结果
				System.out.print(bnum1 + "+" + bnum2 + "=");// 输出题目
				}
			else if(operation==1){
				rightResult = bnum1.subtract(bnum2).doubleValue();
				System.out.print(bnum1 + "-" + bnum2 + "=");
				}
			else if(operation==2){
				rightResult = bnum1.multiply(bnum2).doubleValue();
				System.out.print(bnum1 + "*" + bnum2 + "=");
				}
			else {
				if (num2 == 0) {
				num2 = num2 + 1;
				}
				rightResult = bnum1.divide(bnum2,2,BigDecimal.ROUND_HALF_UP).doubleValue();
				System.out.print(bnum1 + "/" + bnum2 + "=");
			   }
			inputResult = inPutResult();
			if (rightResult == inputResult) {
			rightNum += 1;
			System.out.print("\t正确");
			System.out.println();
			} else {
			System.out.printf("\t错误,正确答案为：" +rightResult);
			System.out.println();
			}
		}
		showResult(questionNum);
	}
	public static double inPutResult() {// 用户输入答案
		double result = -1;
		Scanner in=new Scanner(System.in);
		result = in.nextDouble();// 用户输入她的答案
		//input.close();
		return result;
		}
	public static int inputResult() {// 用户输入答案
			int result = -1;
			Scanner in=new Scanner(System.in);
			result = in.nextInt();// 用户输入她的答案
			//input.close();
			return result;
			}
	public static int choseope() { //用户输入运算符
		int operation=-1;
		Scanner in=new Scanner(System.in);
		String opera=in.next();
		char oper=opera.charAt(0);
		if(oper=='+') {
			operation=0;
		}
		else if(oper=='-') {
			operation=1;
		}
		else if(oper=='*') {
			operation=2;
		}
		else if(oper=='/'){
			operation=3;
		}
		else {
			operation=4;
		}
	  return operation;
	}
	}
