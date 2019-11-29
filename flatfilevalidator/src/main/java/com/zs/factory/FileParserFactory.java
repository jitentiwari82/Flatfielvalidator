package com.zs.factory;


public class FileParserFactory {

    enum FileType {
        CSV("csv"), JSON("json"),XML("xml");

        private String type;
        FileType(String type) {
            this.type = type;
        }
        public String getType() {
            return type;
        }
    }

    public static Parser getParser(String fileExt) throws Exception {
        if (FileType.CSV.getType().equalsIgnoreCase(fileExt)){
            return new CSVParser();
        } else {
            throw new Exception("Implementation Not Available");
        }

    }


}
