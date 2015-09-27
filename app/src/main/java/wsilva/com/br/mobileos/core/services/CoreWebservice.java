package wsilva.com.br.mobileos.core.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpResponseException;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import wsilva.com.br.mobileos.core.vo.WsResult;

public class CoreWebservice<T> {

	private String wsdlURL;
	private String namespace;
	private String operation;
	private Object response;
	private String exception;
	private LinkedHashMap<String, Object> params;
	private WsResult wsResult;
	
	public CoreWebservice() {
		this.params = new LinkedHashMap<String, Object>();
	}

	public CoreWebservice(String wsdlURL, String namespace, String operation)
	{
		this.wsdlURL = wsdlURL;
		this.namespace = namespace;
		this.operation = operation;
		this.params = new LinkedHashMap<String, Object>();
		this.wsResult = new WsResult();
		this.wsResult.result = new ArrayList<T>();
	}
	
	public Object call() throws HttpResponseException, IOException, XmlPullParserException
	{
		if (!isReady()) {
			throw new IllegalStateException("You need setup all needed data before call.");
        }
		
		//Namespace
		SoapObject soap= new SoapObject(namespace, operation);
		
		//Parametros
		if(params != null) 
		{
            for (String key : params.keySet()) 
            {
                Object value = params.get(key);
                PropertyInfo propertyInfo = new PropertyInfo();
                propertyInfo.name = key;
                propertyInfo.setType(value.getClass());
                propertyInfo.setValue(new SoapPrimitive(namespace, key, value.toString()));
                //Adiciona o parametro no Soap
                soap.addProperty(propertyInfo);
            }
        }		
		
		//Cria envelope
		SoapSerializationEnvelope envelope=new SoapSerializationEnvelope(SoapEnvelope.VER11);
		envelope.setOutputSoapObject(soap);
		
		//Cria transporte
		HttpTransportSE transport=new HttpTransportSE(wsdlURL);
		
		//Requisição		
		transport.call(wsdlURL, envelope);
		
		//Recupera retorno 
		Object response = (Object) envelope.getResponse();
		
		if (response!=null) {
			this.response = response;
		}

		return this.response;
	}
	
	public void addProperty(String name, Object value)
	{
		this.params.put(name, value);
	}
	
	private boolean isReady()
	{
        return wsdlURL != null 
        	&& namespace != null
        	&& operation != null;
    }
	
	@SuppressWarnings("unchecked")
	public WsResult getWsResult() throws JSONException
	{
		if (this.response!=null)
		{
			//Obtem resposta
			JSONArray jsonArray = new JSONArray(response.toString());
				
			//Mensagem
			if (jsonArray.length() > 0)
			{	
				wsResult.errorCode = jsonArray.getJSONObject(0).getString("errorCode");
				wsResult.errorMensage = jsonArray.getJSONObject(0).getString("errorMensage");
				wsResult.erroType = jsonArray.getJSONObject(0).getString("erroType");
				
				//Resultado
				JSONArray r = jsonArray.getJSONObject(0).getJSONArray("result");
				
				int qtd = r.length();
				for (int i=0; i<qtd; i++){
					//wsResult.result.add(new GenericVO(r.getJSONObject(i)));
				}
			}
		}
		return wsResult;
	}

	public String getWsdlURL() {
		return wsdlURL;
	}

	public void setWsdlURL(String wsdlURL) {
		this.wsdlURL = wsdlURL;
	}

	public String getNamespace() {
		return namespace;
	}

	public void setNamespace(String namespace) {
		this.namespace = namespace;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public String getException() {
		return exception;
	}

	public void setException(String exception) {
		this.exception = exception;
	}
}