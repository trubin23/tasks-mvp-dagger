package ru.trubin23.tasks_mvp_dagger.di;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by Andrey on 23.02.2018.
 */

@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface ActivityScoped {
}
