package br.com.jdlm.stock_service.service;

import br.com.jdlm.stock_service.domain.entity.movement_type.MovementType;
import br.com.jdlm.stock_service.domain.entity.stock.Stock;
import br.com.jdlm.stock_service.domain.entity.stock.StockDTO;
import br.com.jdlm.stock_service.domain.entity.stock_movement.StockMovement;
import br.com.jdlm.stock_service.domain.entity.stock_movement.StockMovementDTO;
import br.com.jdlm.stock_service.domain.repository.StockMovementRepository;
import br.com.jdlm.stock_service.domain.repository.StockRepository;
import br.com.jdlm.stock_service.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class StockService {

    private final StockRepository stockRepository;
    private final StockMovementRepository stockMovementRepository;

    public Stock createFirstEntry(StockDTO receivedFirstStock) {
        Stock stockFirstEntry = new Stock();

        stockFirstEntry.setActualStock(receivedFirstStock.actualStock());
        stockFirstEntry.setProductId(receivedFirstStock.productId());

        return stockRepository.save(stockFirstEntry);
    }

    public StockMovement registerMovement(StockMovementDTO receivedStockMovement) {
        Optional<Stock> relatedStock = Optional.ofNullable(stockRepository.findById(receivedStockMovement.stock().getId()).orElseThrow(() -> new CustomException("Stock Id not found!!", HttpStatus.NOT_FOUND)));
        StockMovement relatedStockMovement = new StockMovement();

        if (receivedStockMovement.movementType().equals(MovementType.ENTRY)) {
            relatedStock.ifPresent(stock -> stock.incraseStock(receivedStockMovement.movementQuantity()));
        }

        if (receivedStockMovement.movementType().equals(MovementType.OUTPUT)) {
            relatedStock.ifPresent(stock -> stock.decreaseStock(receivedStockMovement.movementQuantity()));
        }

        relatedStock.ifPresent(relatedStockMovement::setStockId);
        relatedStockMovement.setMovementQuantity(receivedStockMovement.movementQuantity());
        relatedStockMovement.setMovementType(receivedStockMovement.movementType());
        return stockMovementRepository.save(relatedStockMovement);
    }

    public List<Stock> getAllStocks() {
        return stockRepository.findAll();
    }

    public Stock getStockByProductId(UUID id) {
        return stockRepository.findByProductId(id);
    }

    public void deleteStockMovement(UUID id) {
        StockMovement relatedStockMovement = stockMovementRepository.findById(id).orElseThrow(() -> new CustomException("Stock Movement not found!", HttpStatus.NOT_FOUND));
        Stock relatedStock = stockRepository.findById(relatedStockMovement.getStockId().getId()).orElseThrow(() -> new CustomException("Stock not found", HttpStatus.NOT_FOUND));

        if (relatedStockMovement.getMovementType().equals(MovementType.OUTPUT)) {
            relatedStock.incraseStock(relatedStockMovement.getMovementQuantity());
        }

        if (relatedStockMovement.getMovementType().equals(MovementType.ENTRY)) {
            relatedStock.decreaseStock(relatedStockMovement.getMovementQuantity());
        }

        stockMovementRepository.deleteById(id);
    }
}
