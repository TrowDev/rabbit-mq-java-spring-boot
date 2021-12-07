package com.microservico.demo.controller;

import com.microservico.demo.service.RabbitMQService;
import constants.RabbitmqConstants;
import dto.EstoqueDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/estoque")
public class EstoqueController {

    @Autowired
    private RabbitMQService rabbitMQService;

    @PutMapping()
    private ResponseEntity alteraEstoque(@RequestBody EstoqueDTO estoqueDTO) {
        System.out.println(estoqueDTO.codigoProduto);
        this.rabbitMQService.enviaMensagem(RabbitmqConstants.FILA_ESTOQUE, estoqueDTO);
        return new ResponseEntity(HttpStatus.OK);
    }

}
