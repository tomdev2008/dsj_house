<#include "common/taglibs.ftl">
	<link rel="stylesheet" href="${ctx }/static/front/css/dsj_newhouse.css">
	<link rel="stylesheet" href="${ctx }/static/front/css/dsj_newhouse_list.css">
	<link rel="stylesheet" type="text/css" href="${ctx }/static/front/css/warrant.css">
  	<div class="warrant">
	  	<table width="70%" border="0" align="center" cellpadding="5" cellspacing="0" style="border:solid 1px #107929">
			<tr>
		  		<th align="center" height="30" colspan="5" bgcolor="#6BBE18">
					订单支付结果通知
				</th>
		  	</tr>
	
			<tr>
				<td width="30%" align="right">商户编号[customernumber]：</td>
				<td width="70%" align="left"> ${dataMap.customernumber} </td>
			</tr>
	
			<tr>
				<td width="30%" align="right">商户订单号[requestid]：</td>
				<td width="70%" align="left"> ${dataMap.requestid} </td>
			</tr>
	
			<tr>
				<td width="30%" align="right">返回码[code]：</td>
				<td width="70%" align="left"> ${dataMap.code} </td>
			</tr>
	
			<tr>
				<td width="30%" align="right">通知类型[notifytype]：</td>
				<td width="70%" align="left"> ${dataMap.notifytype} </td>
			</tr>
	
			<tr>
				<td width="30%" align="right">交易流水号[externalid]：</td>
				<td width="70%" align="left"> ${dataMap.externalid} </td>
			</tr>
	
			<tr>
				<td width="30%" align="right">订单金额[amount]：</td>
				<td width="70%" align="left"> ${dataMap.amount} </td>
			</tr>
	
			<tr>
				<td width="30%" align="right">银行卡号[cardno]：</td>
				<td width="70%" align="left"> ${dataMap.cardno} </td>
			</tr>
	
			<tr>
				<td width="30%" align="right">银行编码[bankcode]：</td>
				<td width="70%" align="left"> ${dataMap.bankcode} </td>
			</tr>
	
			<tr>
				<td width="30%" align="right">银行卡类型[cardtype]：</td>
				<td width="70%" align="left"> ${dataMap.cardtype} </td>
			</tr>
	
			<tr>
				<td width="30%" align="right">支付成功时间[paydate]：</td>
				<td width="70%" align="left"> ${dataMap.paydate} </td>
			</tr>
	
			<tr>
				<td width="30%" align="right">支付方式[payProduct]：</td>
				<td width="70%" align="left"> ${dataMap.payProduct} </td>
			</tr>
		</table>
	</div>
    <script type="text/javascript">
    	
        
    </script>
