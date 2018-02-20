package com.recipe.app.services;

import org.springframework.web.multipart.MultipartFile;

/**
 * Created by alex.andrade on 19/02/2018.
 */
public interface ImageService {
    void saveImageFile(Long recipeId, MultipartFile file);
}
