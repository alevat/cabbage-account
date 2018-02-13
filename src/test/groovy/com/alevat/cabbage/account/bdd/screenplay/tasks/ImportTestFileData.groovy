package com.alevat.cabbage.account.bdd.screenplay.tasks

import com.alevat.cabbage.account.domain.ImportFormat

class ImportTestFileData {

    private final ImportFormat format
    private final File file

    static ImportTestFileData get(String typeName) {
        def formatName = typeName.toUpperCase().replace(' ', '_')
        def format = ImportFormat.valueOf(formatName)
        def filename = format.name().toLowerCase() + "_import_file.csv"
        def file = ImportTestFileData.class.getResource("data/${filename}").getFile()
        return new ImportTestFileData(format: format, file: file)
    }

    File getFile() {
        return file
    }

    ImportFormat getFormat() {
        return format
    }

}
