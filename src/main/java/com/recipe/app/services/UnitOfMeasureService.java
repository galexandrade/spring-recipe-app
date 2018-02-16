package com.recipe.app.services;

import com.recipe.app.commands.UnitOfMeasureCommand;

import java.util.Set;

/**
 * Created by alex.andrade on 16/02/2018.
 */
public interface UnitOfMeasureService {
    Set<UnitOfMeasureCommand> listAllUoms();
}
