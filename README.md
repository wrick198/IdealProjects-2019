# IdealProjects-2019
Personal practice daily

- 第一次上传 2019-5-29
- Leetcode 07 数据类型相关的问题
	- 增加数据类型的长度，L f d
	- 除法计算要用 double float
	- 更加精确的计算使用 BigDecimal 
- LC08
```
if(numberint>Integer.MAX_VALUE/10 || (numberint==Integer.MAX_VALUE/10 && a>7)) //比最大值大,提前判断计算结果的值
     return Integer.MAX_VALUE;
if(numberint<Integer.MIN_VALUE/10 || (numberint==Integer.MIN_VALUE/10 && a<-8)) //比最大值小
     return Integer.MIN_VALUE;
     numberint=numberint*10+a;
     
while(i<length && str.charAt(i)==' ')
      i++;      
 //判断之前需要先检查下标是否越界     
if(i<length && str.charAt(i)=='-'){ //负号
     negflag=true;
     i++;
}else if(i<length && str.charAt(i)=='+'){ //正号
     i++;
}

```
	
