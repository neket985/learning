package com.example.learning;

import com.example.learning.gen.tables.records.JArticleRecord;
import org.jooq.CloseableDSLContext;
import org.jooq.Result;
import org.jooq.impl.DSL;

import java.util.List;

import static com.example.learning.gen.Tables.ARTICLE;
import static com.example.learning.gen.Tables.AUTHOR;

public class Main {

    public static void main(String[] args) {
        Meteostation meteo1 = new Meteostation(1, 750);
        CloseableDSLContext context = DSL.using(
                "jdbc:postgresql://localhost:5432/postgres",
                "postgres",
                "password"
        );
        context.fetch("""
                select * from article
                """.trim());

        Result<JArticleRecord> jArticleRecords = context.select(ARTICLE.asterisk()).from(ARTICLE)
                .join(AUTHOR)
                .on(AUTHOR.ID.eq(ARTICLE.AUTHOR_ID))
                .fetchInto(ARTICLE);

        List<String> map = jArticleRecords.map((r) -> {
            return r.getTitle();
        });

        System.out.println(jArticleRecords);
    }
}
