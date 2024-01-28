package com.example.springlearning;

import com.example.springlearning.entity.thrift.TModel;
import com.example.springlearning.service.thrift.TModelService;
import lombok.extern.slf4j.Slf4j;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TIOStreamTransport;
import org.apache.thrift.transport.TMemoryBuffer;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.io.ByteArrayInputStream;

@SpringBootTest
@Slf4j
public class TServiceTest {
    @Resource(name = "tModelServiceImpl")
    private TModelService tModelService;

    @Test
    public void test() {
        LOGGER.info("queryModel = {}", tModelService.queryModel());
    }

    @Test
    public void thriftSerializeTest() throws TException {
        // Create a Thrift object
        TModel tModel = new TModel();

        // Serialize the Thrift object
        TMemoryBuffer buffer = new TMemoryBuffer(1024);
        TProtocol protocol = new TBinaryProtocol.Factory().getProtocol(buffer);
        tModel.write(protocol);

        byte[] serializedData = buffer.getArray();

        // Deserialize the Thrift object
        ByteArrayInputStream inputStream = new ByteArrayInputStream(serializedData);
        TIOStreamTransport transport = new TIOStreamTransport(inputStream);
        TProtocol protocol1 = new TBinaryProtocol.Factory().getProtocol(transport);

        TModel deserializedTModel = new TModel();
        deserializedTModel.read(protocol1);
        LOGGER.info("tModel = {}", tModel);
    }
}
