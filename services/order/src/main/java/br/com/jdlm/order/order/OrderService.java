package br.com.jdlm.order.order;

import br.com.jdlm.order.custumer.CustomerClient;
import br.com.jdlm.order.exception.BusinessException;
import br.com.jdlm.order.kafka.OrderConfirmation;
import br.com.jdlm.order.kafka.OrderProducer;
import br.com.jdlm.order.orderline.OrderLineRequest;
import br.com.jdlm.order.orderline.OrderLineService;
import br.com.jdlm.order.product.ProductClient;
import br.com.jdlm.order.product.PurchaseRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final CustomerClient customerClient;
    private final ProductClient productClient;
    private final OrderMapper mapper;
    private final OrderLineService orderLineService;
    private final OrderProducer orderProducer;

    public Integer createdOrder(OrderRequest request) {

        var customer = this.customerClient.findCustomerById(request.customerId())
                .orElseThrow(() ->
                        new BusinessException("Cannot create order:: No Customer exists with the provided ID"));


        var purchaseProducts = this.productClient.purchaseProducts(request.products());

        var order = this.orderRepository.save(this.mapper.toOrder(request));

        for(PurchaseRequest purchaseRequest : request.products()){
            orderLineService.saveOrderLine(
                    new OrderLineRequest(
                            null,
                            order.getId(),
                            purchaseRequest.productId(),
                            purchaseRequest.quantity()
                    )
            );
        }

        //TODO START PAYMENT PROCESS

        orderProducer.sendOrderConfirmation(
                new OrderConfirmation(
                        request.reference(),
                        request.amount(),
                        request.paymentMethod(),
                        customer,
                        purchaseProducts
                )
        );

        return order.getId();
    }
}
