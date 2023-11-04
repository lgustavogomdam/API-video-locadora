package io.github.lgustavogomdam.api_video_locadora.controller;


import io.github.lgustavogomdam.api_video_locadora.model.dto.ItemModel;
import io.github.lgustavogomdam.api_video_locadora.service.ItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ItemAPIController extends AbstractAPIController<ItemModel,Long, ItemService> {

    @Override
    @PostMapping
    public ResponseEntity<ItemModel> create(@RequestBody ItemModel model) {

        if(model.getId() != null)
            model.setId(null);

        return new ResponseEntity<ItemModel>(this.service.create(model), HttpStatus.CREATED);
    }

    @Override
    @PutMapping
    public ResponseEntity<ItemModel> update(@RequestBody ItemModel model) {

        if (model.getId() == null)
            throw new RuntimeException("O ID do elemento não pode ser NULL");

        return new ResponseEntity<ItemModel>(this.service.update(model), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<String> delete(@PathVariable Long id) {
        ItemModel model = this.service.findById(id);
        this.service.softDelete(id);
        return new ResponseEntity<String>(String.format(
                "O Item %s foi deletado com sucesso!",
                model.getTitulo().toUpperCase()
        ),HttpStatus.OK);
    }
}
