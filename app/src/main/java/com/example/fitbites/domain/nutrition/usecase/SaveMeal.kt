package com.example.fitbites.domain.nutrition.usecase

import com.example.fitbites.repository.nutrition.NutritionRepositoryImpl
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import com.example.fitbites.domain.nutrition.model.Ingredient
import com.example.fitbites.domain.nutrition.model.TotalMeal
import com.example.fitbites.utils.Response

class SaveMeal @Inject constructor(
    private val repository: NutritionRepositoryImpl
) {
    suspend operator fun invoke(totalMeal: TotalMeal, ingredients: List<Ingredient>): Flow<Response<Boolean>> {
        return repository.saveMeal(totalMeal, ingredients)
    }
}
