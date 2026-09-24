package com.Vy.telegram_bot.mapper;

import com.Vy.telegram_bot.dto.oyak.OyakApiRequest;
import com.Vy.telegram_bot.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    Product toProduct(OyakApiRequest oyakApiRequest);
}
