# sendemail

## Aplicação Java que faz o envio de email através do Outlook/Hotmail, no exemplo os envios ocorrem de 1 em 1 minuto.

### Após subir o SpringBoot, acesse via Postman a URL abaixo:

http://localhost:8080/send-email/send


### E Informe os dados abaixo no body:

```JSON
{
  "to": "aqui vai o email que irá receber a mensagem",
  "subject": "Assunto do email",
  "body": "Conteúdo do email Exemplo: Estou aqui para testar o envio de email a cada 1 minuto."
}
```