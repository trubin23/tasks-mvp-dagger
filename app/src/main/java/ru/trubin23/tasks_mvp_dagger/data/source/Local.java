package ru.trubin23.tasks_mvp_dagger.data.source;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;

/**
 * Created by Andrey on 20.02.2018.
 */

@Qualifier
@Retention(RetentionPolicy.RUNTIME)
@interface Local {

}
