package com.alevat.cabbage.account.bdd.screenplay.tasks

import com.alevat.cabbage.account.domain.ImportFormat

class ImportTestFileData {

    private ImportFormat format
    private File file

    static ImportTestFileData get(String typeName) {
        def formatName = typeName.toUpperCase().replace(' ', '_')
        def format = ImportFormat.valueOf(formatName)
        def filename = format.name().toLowerCase() + "_import_file.csv"
        def filePath = ImportTestFileData.class.getResource("/data/${filename}").getFile()
        def file = new File(filePath)
        return new ImportTestFileData(format: format, file: file)
    }

    File getFile() {
        return file
    }

    ImportFormat getFormat() {
        return format
    }

}
