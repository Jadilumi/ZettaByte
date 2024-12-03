package br.com.jdlm.stock_service.controller;

import br.com.jdlm.stock_service.domain.entity.stock.Stock;
import br.com.jdlm.stock_service.domain.entity.stock.StockDTO;
import br.com.jdlm.stock_service.domain.entity.stock_movement.StockMovement;
import br.com.jdlm.stock_service.domain.entity.stock_movement.StockMovementDTO;
import br.com.jdlm.stock_service.service.StockService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/stocks")
@RequiredArgsConstructor
public class StockController {

    private final StockService stockService;

    @PostMapping("/first-entry")
    public ResponseEntity<Stock> createFirstEntry(@RequestBody StockDTO receivedFirstStock) {
        return new ResponseEntity<>(stockService.createFirstEntry(receivedFirstStock), HttpStatus.CREATED);
    }

    @PostMapping("/register-movement")
    public ResponseEntity<StockMovement> registerMovement(@RequestBody StockMovementDTO receivedStockMovementDTO) {
        return new ResponseEntity<>(stockService.registerMovement(receivedStockMovementDTO), HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<List<Stock>> getAllStocks() {
        return new ResponseEntity<>(stockService.getAllStocks(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Stock> getStockByProductId(@PathVariable UUID id) {
        return new ResponseEntity<>(stockService.getStockByProductId(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStockMovementById(@PathVariable UUID id) {
        stockService.deleteStockMovement(id);
        return new ResponseEntity<>("Movement deleted and stock updated!", HttpStatus.GONE);
    }
}
