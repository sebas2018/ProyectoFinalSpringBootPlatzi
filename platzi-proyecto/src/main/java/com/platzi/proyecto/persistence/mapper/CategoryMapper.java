package com.platzi.proyecto.persistence.mapper;

import com.platzi.proyecto.domain.Category;
import com.platzi.proyecto.persistence.entity.Categoria;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring") // componente de tipo spring
public interface CategoryMapper {
    //------------Convertimos una Categoria a  Category---------------------
    @Mappings({
            @Mapping(source = "idCategoria", target = "idCategory"),
            @Mapping(source = "descripcion", target = "description"),
            @Mapping(source = "estado", target = "status")
    })
    Category toCategory(Categoria categoria);
    //------------------------------------------------------------------------

    //-------------------Convertimos una Categoria a  Category--------------------
    //No tenemos que definir nuevamente (@Mappings) simplemente utilizamos la anotacion @InheritInverseConfiguration y
    //mapstruct internamente sabe que tiene que hacer el mapeo inverso al que ua se realizo anteriormente
    @InheritInverseConfiguration // esta anotacion le indica a mapstruct que la siguiente conversion es la inversa a la anterior conversion
    @Mapping(target = "productos", ignore = true)
    Categoria toCategoria(Category category);
    //NOTA: Como en Categoria tenemos productos y en Category no por que no lo vamos a mapear
    //tenemos que especificarlo con la propiedad (ignore = true)
    //----------------------------------------------------------------------------
}
