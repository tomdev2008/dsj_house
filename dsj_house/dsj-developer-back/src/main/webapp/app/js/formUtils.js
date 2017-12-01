 
function getElements(formId) {    
    var form = document.getElementById(formId);    
    var elements = new Array();    
    var tagElements = form.getElementsByTagName('input'); 
    
    var selectElements=form.getElementsByTagName('select'); 
    for (var j = 0; j < tagElements.length; j++){  
         elements.push(tagElements[j]);  

    }  

    for (var j = 0; j < selectElements.length; j++){  
        elements.push(selectElements[j]);  
 
   }  
    return elements;    
}   
  

function inputSelector(element) {    
  if (element.checked)    
     return [element.name, element.value];    
}  

function selectSelector(element){
	return [element.name, element.value];   
}
      
function input(element) { 
    switch (element.type.toLowerCase()) {    
      case 'submit':    
      case 'hidden':    
      case 'password':    
      case 'text':    
        return [element.name, element.value];    
      case 'checkbox':    
      case 'radio':     
        return inputSelector(element);  
      case 'select-one': 
    	  return  selectSelector(element);  
    }    
    return false;    
}    
  

function serializeElement(element) {    
    var method = element.tagName.toLowerCase();    
    var parameter = input(element);    
    var results = [];    
    if (parameter) {    
      var key = parameter[0];    
      if (key.length == 0) return;    
    
      if (parameter[1].constructor != Array)    
        parameter[1] = [parameter[1]];    
          
      var values = parameter[1];    
      
      for (var i=0; i<values.length; i++) {    
    
    	   results.push(key + '=' + values[i]);  
      } 
      return results.join('&');    
    }    
 }    
  
function aoDataChange(searchFromId,aoData ){
	var elements = getElements(searchFromId);
	for (var i = 0; i < elements.length; i++) {    
	      var queryComponent = serializeElement(elements[i]);    
	      if (queryComponent)    
	    	  aoData.push( { "name":queryComponent.split("=")[0], "value": queryComponent.split("=")[1] } );
	    }  
	
	return aoData;
}

function formDataChange(searchFromId,aoData ){
	var elements = getElements(searchFromId);
	for (var i = 0; i < elements.length; i++) {    
	      var queryComponent = serializeElement(elements[i]);  
	     // var name="'"+queryComponent.split("=")[0]+"'";
	      if (queryComponent)    
	    	  aoData[queryComponent.split("=")[0]]=queryComponent.split("=")[1];
	    }  
	
	return aoData;
}
   
   
function serializeForm(formId) {    
    var elements = getElements(formId);    
    var queryComponents = new Array();    
    
    for (var i = 0; i < elements.length; i++) {    
      var queryComponent = serializeElement(elements[i]);    
      if (queryComponent)    
        queryComponents.push(queryComponent);    
    }    
    
    return queryComponents.join('&');  
}    
//序列化转json对象  方法一(bug)
var DataToJson = {  
		//将从form中通过$('#form').serialize()获取的值转成json  
		           formToJson: function (data) {  
		               data=data.replace(/&/g,"\",\"");  
		               data=data.replace(/=/g,"\":\"");  
		               data="{\""+data+"\"}";  
		               return data;  
		            },  
};  
//序列化转json对象 方法二
$.fn.serializeObject = function()
{
    var o = {};
    var a = this.serializeArray();
    $.each(a, function() {
        if (o[this.name] !== undefined) {
          
            o[this.name]= o[this.name] + "," +this.value;
        } else {
        	if(this.value!=""&&this.value!=null){
        		 o[this.name] = this.value || '';
        	}
        }
    });
    return o;
}; 


function GetQueryString(name)
{
     var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
     var r = window.location.search.substr(1).match(reg);
     if(r!=null)return  unescape(r[2]); return null;
}