package com.example.team11.Service;

import com.example.team11.DTO.CartDTO;
import com.example.team11.DTO.CartItemDTO;
import com.example.team11.Entity.Cart;
import com.example.team11.Repository.CartRepository;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

@Service
public class CartService {
    private final CartRepository cartRepository;

    public CartService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    public CartDTO getOrCreateCartForUser(Long userId) {
        Cart cart = cartRepository.findByUserId(userId);
        if (cart == null) {
            cart = new Cart();
            cart.setUserId(userId);
            cart.setTotalPrice(0.0);
            cart = cartRepository.save(cart);
        }
        return convertToDTO(cart);
    }

    public CartDTO getCartById(Long id) {
        Cart cart = cartRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cart not found"));
        return convertToDTO(cart);
    }

    public void deleteCart(Long id) {
        Cart cart = cartRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cart not found"));
        cartRepository.delete(cart);
    }

    private CartDTO convertToDTO(Cart cart) {
        CartDTO cartDTO = new CartDTO();
        cartDTO.setId(cart.getId());
        cartDTO.setTotalPrice(cart.getTotalPrice());

        List<CartItemDTO> cartItemDTOs = cart.getItems().stream()
                .map(item -> {
                    CartItemDTO cartItemDTO = new CartItemDTO();
                    cartItemDTO.setId(item.getId());
                    cartItemDTO.setProductName(item.getProductName());
                    cartItemDTO.setQuantity(item.getQuantity());
                    cartItemDTO.setPrice(item.getPrice());
                    return cartItemDTO;
                })
                .collect(Collectors.toList());

        cartDTO.setItems(cartItemDTOs);
        return cartDTO;
    }
}