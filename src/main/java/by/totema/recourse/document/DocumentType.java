package by.totema.recourse.document;

import by.totema.recourse.document.generator.DocumentGenerator;
import by.totema.recourse.document.generator.impl.CsvGenerator;
import by.totema.recourse.document.generator.impl.PdfGenerator;
import by.totema.recourse.document.generator.impl.XlsxGenerator;
import by.totema.recourse.document.model.provider.ContentProvider;

public enum DocumentType {
    PDF {
        @Override
        public <TMainEntity, TTableEntity> DocumentGenerator<TMainEntity, TTableEntity> createGenerator(ContentProvider<TMainEntity, TTableEntity> contentProvider) {
            return new PdfGenerator<>(contentProvider);
        }
    },

    XLSX {
        @Override
        public <TMainEntity, TTableEntity> DocumentGenerator<TMainEntity, TTableEntity> createGenerator(ContentProvider<TMainEntity, TTableEntity> contentProvider) {
            return new XlsxGenerator<>(contentProvider);
        }
    },

    CSV {
        @Override
        public <TMainEntity, TTableEntity> DocumentGenerator<TMainEntity, TTableEntity> createGenerator(ContentProvider<TMainEntity, TTableEntity> contentProvider) {
            return new CsvGenerator<>(contentProvider);
        }
    };

    public abstract <TMainEntity, TTableEntity> DocumentGenerator<TMainEntity, TTableEntity> createGenerator(ContentProvider<TMainEntity, TTableEntity> contentProvider);
}