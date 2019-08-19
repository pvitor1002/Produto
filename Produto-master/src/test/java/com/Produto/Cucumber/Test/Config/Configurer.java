package com.Produto.Cucumber.Test.Config;

import java.util.Locale;
import java.util.Map;

import com.Produto.Model.Produto;

import cucumber.api.TypeRegistry;
import cucumber.api.TypeRegistryConfigurer;
import io.cucumber.datatable.DataTableType;
import io.cucumber.datatable.TableEntryTransformer;

public class Configurer implements TypeRegistryConfigurer {

    @Override
            public void configureTypeRegistry(TypeRegistry registry) {

    registry.defineDataTableType(new DataTableType(Produto.class, new TableEntryTransformer<Produto>() {
                    @Override
                    public Produto transform(Map<String, String> entry) {
                        return new Produto(entry.get("nome"), Double.parseDouble(entry.get("valor")), Integer.parseInt(entry.get("id")));
                    }
                }));
            }

            @Override
            public Locale locale() {
                return Locale.ENGLISH;
            }

        }