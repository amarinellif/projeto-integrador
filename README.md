<h1 align="center"> 🍃 MeLi Frescos - Requisito 6 🍃 </h1>
<p align="center"> Requisito Final do último Touchpoint do Bootcamp IT Java.</p>



## 🖊 Sobre a proposta da implementação


<p> 
 Para decidir qual solução implementar, optei por olhar o problema pelo viés do agente. Um dos grandes desafios de manter
um armazém de produtos fresh é devido ao prazo de validade reduzido e o armazenamento dos produtos, que precisa contar com um controle rigoso de temperatura para que não haja contaminação, nem perda de produtos. 

Dessa forma, busquei trazer informações para fomentar as tomadas de decisão do agente do armazém, sendo elas:

1 - Lista de lotes de produtos que estão com a temperatura acima do mínimo ideal.

Get: `api/v1/fresh-products/agent/warning-temp-batches/2`

```json
{
    "idWarehouse": 2,
    "nameWarehouse": "Armazem B",
    "batchWarningTempDtoList": [
        {
            "batchId": 4,
            "productName": "Iogurte",
            "orderNumber": 2,
            "currentTemperature": 1.0,
            "minimumTemperature": 0.0
        }
    ]
}
```
 

2 - Lista de lotes de produtos que estão próximos de um período especIficado de dias para expirar.
 
 Get: `api/v1/fresh-products/agent/warning-duedate-batches/1?daysUntil=60`
 
 ```json
 {
    "idWarehouse": 1,
    "nameWarehouse": "Armazem A",
    "batchWarningDueDateDto": [
        {
            "batchId": 1,
            "productName": "Peito de Frango",
            "orderNumber": 1,
            "dueDate": "2022-09-30",
            "daysToExpire": 44
        },
        {
            "batchId": 2,
            "productName": "Carne",
            "orderNumber": 1,
            "dueDate": "2022-10-12",
            "daysToExpire": 56
        }
    ]
}
```
3 - Lista de seções de produtos que estão com temperetura fora da faixa especificada.

Get: `api/v1/fresh-products/agent/warning-temp-section/1`
 
  ```json
{
    "idWarehouse": 1,
    "nameWarehouse": "Armazem A",
    "sectionWarningTempDtoList": [
        {
            "sectionId": 1,
            "sectionType": "Fresco",
            "currentTemperature": 24.0,
            "minimumTemperature": 10.0,
            "maximumTemperature": 18.0
        },
        {
            "sectionId": 3,
            "sectionType": "Congelado",
            "currentTemperature": 0.0,
            "minimumTemperature": -45.0,
            "maximumTemperature": -25.0
        }
    ]
}
```

4 - Lista de lotes de produtos que possivelmente estão na seção errada, através da comparação da temperatura ideal com a temperatura da seção.

Get: `api/v1/fresh-products/agent/wrong-place-batches/2`
 
```json
{
    "idWarehouse": 2,
    "nameWarehouse": "Armazem B",
    "batchWarningTempDtoList": [
        {
            "minimum_temperature_section": 0.0,
            "maximum_temperature_section": 10.0,
            "batch_id": 8,
            "product_id": 4,
            "product_name": "Uva",
            "product_type": "Fresco",
            "minimum_temperature": 15.0
        }
    ]
}
```
 
</p>


___
## 📄 Documentação

Para possibilitar melhor compreensão do código o projeto possui uma pasta de documentação
onde está a história do usuário, o Script base para o banco de dados, a coleção do postman, o Diagrama Entidade Relacionamento.

Ainda, o projeto possui Java Doc:

1 - À partir da IDE de sua preferência, basta rodar o comando: `mvn javadoc:javadoc`. Esse comando fará o build da documentação e criará uma pasta chamada **target**.

2 - Agora basta localizar o arquivo `index-all.html` -> clicar com o botão direito em "Open" -> escolher o browser de sua preferência. Ao clicar, será aberta uma página contendo a documentação.

___


## 📝 Autora ##

Desenvolvido por Amanda Marinelli Ferreira 💛

