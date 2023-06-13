package io.pigeon.access;// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: person.proto

/**
 * <pre>
 * define person message
 * </pre>
 *
 * Protobuf type {@code Person}
 */
public final class Person extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:Person)
        PersonOrBuilder {
private static final long serialVersionUID = 0L;
  // Use Person.newBuilder() to construct.
  private Person(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private Person() {
    name_ = "";
    nums_ = emptyIntList();
    integers_ = emptyIntList();
  }

  @Override
  @SuppressWarnings({"unused"})
  protected Object newInstance(
      UnusedPrivateParameter unused) {
    return new Person();
  }

  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return PersonOuterClass.internal_static_Person_descriptor;
  }

  @Override
  protected FieldAccessorTable
      internalGetFieldAccessorTable() {
    return PersonOuterClass.internal_static_Person_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            Person.class, Builder.class);
  }

  public static final int ID_FIELD_NUMBER = 1;
  private long id_ = 0L;
  /**
   * <code>uint64 id = 1;</code>
   * @return The id.
   */
  @Override
  public long getId() {
    return id_;
  }

  public static final int NAME_FIELD_NUMBER = 2;
  @SuppressWarnings("serial")
  private volatile Object name_ = "";
  /**
   * <code>string name = 2;</code>
   * @return The name.
   */
  @Override
  public String getName() {
    Object ref = name_;
    if (ref instanceof String) {
      return (String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      String s = bs.toStringUtf8();
      name_ = s;
      return s;
    }
  }
  /**
   * <code>string name = 2;</code>
   * @return The bytes for name.
   */
  @Override
  public com.google.protobuf.ByteString
      getNameBytes() {
    Object ref = name_;
    if (ref instanceof String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (String) ref);
      name_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int AGE_FIELD_NUMBER = 3;
  private int age_ = 0;
  /**
   * <code>int32 age = 3;</code>
   * @return The age.
   */
  @Override
  public int getAge() {
    return age_;
  }

  public static final int NUMS_FIELD_NUMBER = 4;
  @SuppressWarnings("serial")
  private com.google.protobuf.Internal.IntList nums_;
  /**
   * <code>repeated int32 nums = 4;</code>
   * @return A list containing the nums.
   */
  @Override
  public java.util.List<Integer>
      getNumsList() {
    return nums_;
  }
  /**
   * <code>repeated int32 nums = 4;</code>
   * @return The count of nums.
   */
  public int getNumsCount() {
    return nums_.size();
  }
  /**
   * <code>repeated int32 nums = 4;</code>
   * @param index The index of the element to return.
   * @return The nums at the given index.
   */
  public int getNums(int index) {
    return nums_.getInt(index);
  }
  private int numsMemoizedSerializedSize = -1;

  public static final int INTEGERS_FIELD_NUMBER = 5;
  @SuppressWarnings("serial")
  private com.google.protobuf.Internal.IntList integers_;
  /**
   * <code>repeated int32 integers = 5;</code>
   * @return A list containing the integers.
   */
  @Override
  public java.util.List<Integer>
      getIntegersList() {
    return integers_;
  }
  /**
   * <code>repeated int32 integers = 5;</code>
   * @return The count of integers.
   */
  public int getIntegersCount() {
    return integers_.size();
  }
  /**
   * <code>repeated int32 integers = 5;</code>
   * @param index The index of the element to return.
   * @return The integers at the given index.
   */
  public int getIntegers(int index) {
    return integers_.getInt(index);
  }
  private int integersMemoizedSerializedSize = -1;

  public static final int TIMESTAMP_FIELD_NUMBER = 7;
  private long timestamp_ = 0L;
  /**
   * <code>uint64 timestamp = 7;</code>
   * @return The timestamp.
   */
  @Override
  public long getTimestamp() {
    return timestamp_;
  }

  public static final int ADDRESS_FIELD_NUMBER = 8;
  private Address address_;
  /**
   * <code>.Address address = 8;</code>
   * @return Whether the address field is set.
   */
  @Override
  public boolean hasAddress() {
    return address_ != null;
  }
  /**
   * <code>.Address address = 8;</code>
   * @return The address.
   */
  @Override
  public Address getAddress() {
    return address_ == null ? Address.getDefaultInstance() : address_;
  }
  /**
   * <code>.Address address = 8;</code>
   */
  @Override
  public AddressOrBuilder getAddressOrBuilder() {
    return address_ == null ? Address.getDefaultInstance() : address_;
  }

  private byte memoizedIsInitialized = -1;
  @Override
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    memoizedIsInitialized = 1;
    return true;
  }

  @Override
  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    getSerializedSize();
    if (id_ != 0L) {
      output.writeUInt64(1, id_);
    }
    if (!com.google.protobuf.GeneratedMessageV3.isStringEmpty(name_)) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 2, name_);
    }
    if (age_ != 0) {
      output.writeInt32(3, age_);
    }
    if (getNumsList().size() > 0) {
      output.writeUInt32NoTag(34);
      output.writeUInt32NoTag(numsMemoizedSerializedSize);
    }
    for (int i = 0; i < nums_.size(); i++) {
      output.writeInt32NoTag(nums_.getInt(i));
    }
    if (getIntegersList().size() > 0) {
      output.writeUInt32NoTag(42);
      output.writeUInt32NoTag(integersMemoizedSerializedSize);
    }
    for (int i = 0; i < integers_.size(); i++) {
      output.writeInt32NoTag(integers_.getInt(i));
    }
    if (timestamp_ != 0L) {
      output.writeUInt64(7, timestamp_);
    }
    if (address_ != null) {
      output.writeMessage(8, getAddress());
    }
    getUnknownFields().writeTo(output);
  }

  @Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (id_ != 0L) {
      size += com.google.protobuf.CodedOutputStream
        .computeUInt64Size(1, id_);
    }
    if (!com.google.protobuf.GeneratedMessageV3.isStringEmpty(name_)) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(2, name_);
    }
    if (age_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(3, age_);
    }
    {
      int dataSize = 0;
      for (int i = 0; i < nums_.size(); i++) {
        dataSize += com.google.protobuf.CodedOutputStream
          .computeInt32SizeNoTag(nums_.getInt(i));
      }
      size += dataSize;
      if (!getNumsList().isEmpty()) {
        size += 1;
        size += com.google.protobuf.CodedOutputStream
            .computeInt32SizeNoTag(dataSize);
      }
      numsMemoizedSerializedSize = dataSize;
    }
    {
      int dataSize = 0;
      for (int i = 0; i < integers_.size(); i++) {
        dataSize += com.google.protobuf.CodedOutputStream
          .computeInt32SizeNoTag(integers_.getInt(i));
      }
      size += dataSize;
      if (!getIntegersList().isEmpty()) {
        size += 1;
        size += com.google.protobuf.CodedOutputStream
            .computeInt32SizeNoTag(dataSize);
      }
      integersMemoizedSerializedSize = dataSize;
    }
    if (timestamp_ != 0L) {
      size += com.google.protobuf.CodedOutputStream
        .computeUInt64Size(7, timestamp_);
    }
    if (address_ != null) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(8, getAddress());
    }
    size += getUnknownFields().getSerializedSize();
    memoizedSize = size;
    return size;
  }

  @Override
  public boolean equals(final Object obj) {
    if (obj == this) {
     return true;
    }
    if (!(obj instanceof Person)) {
      return super.equals(obj);
    }
    Person other = (Person) obj;

    if (getId()
        != other.getId()) return false;
    if (!getName()
        .equals(other.getName())) return false;
    if (getAge()
        != other.getAge()) return false;
    if (!getNumsList()
        .equals(other.getNumsList())) return false;
    if (!getIntegersList()
        .equals(other.getIntegersList())) return false;
    if (getTimestamp()
        != other.getTimestamp()) return false;
    if (hasAddress() != other.hasAddress()) return false;
    if (hasAddress()) {
      if (!getAddress()
          .equals(other.getAddress())) return false;
    }
    if (!getUnknownFields().equals(other.getUnknownFields())) return false;
    return true;
  }

  @Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    hash = (37 * hash) + ID_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        getId());
    hash = (37 * hash) + NAME_FIELD_NUMBER;
    hash = (53 * hash) + getName().hashCode();
    hash = (37 * hash) + AGE_FIELD_NUMBER;
    hash = (53 * hash) + getAge();
    if (getNumsCount() > 0) {
      hash = (37 * hash) + NUMS_FIELD_NUMBER;
      hash = (53 * hash) + getNumsList().hashCode();
    }
    if (getIntegersCount() > 0) {
      hash = (37 * hash) + INTEGERS_FIELD_NUMBER;
      hash = (53 * hash) + getIntegersList().hashCode();
    }
    hash = (37 * hash) + TIMESTAMP_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        getTimestamp());
    if (hasAddress()) {
      hash = (37 * hash) + ADDRESS_FIELD_NUMBER;
      hash = (53 * hash) + getAddress().hashCode();
    }
    hash = (29 * hash) + getUnknownFields().hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static Person parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static Person parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static Person parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static Person parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static Person parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static Person parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static Person parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static Person parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  public static Person parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }

  public static Person parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static Person parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static Person parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  @Override
  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  public static Builder newBuilder(Person prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }
  @Override
  public Builder toBuilder() {
    return this == DEFAULT_INSTANCE
        ? new Builder() : new Builder().mergeFrom(this);
  }

  @Override
  protected Builder newBuilderForType(
      BuilderParent parent) {
    Builder builder = new Builder(parent);
    return builder;
  }
  /**
   * <pre>
   * define person message
   * </pre>
   *
   * Protobuf type {@code Person}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:Person)
          PersonOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return PersonOuterClass.internal_static_Person_descriptor;
    }

    @Override
    protected FieldAccessorTable
        internalGetFieldAccessorTable() {
      return PersonOuterClass.internal_static_Person_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              Person.class, Builder.class);
    }

    // Construct using Person.newBuilder()
    private Builder() {

    }

    private Builder(
        BuilderParent parent) {
      super(parent);

    }
    @Override
    public Builder clear() {
      super.clear();
      bitField0_ = 0;
      id_ = 0L;
      name_ = "";
      age_ = 0;
      nums_ = emptyIntList();
      integers_ = emptyIntList();
      timestamp_ = 0L;
      address_ = null;
      if (addressBuilder_ != null) {
        addressBuilder_.dispose();
        addressBuilder_ = null;
      }
      return this;
    }

    @Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return PersonOuterClass.internal_static_Person_descriptor;
    }

    @Override
    public Person getDefaultInstanceForType() {
      return Person.getDefaultInstance();
    }

    @Override
    public Person build() {
      Person result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @Override
    public Person buildPartial() {
      Person result = new Person(this);
      buildPartialRepeatedFields(result);
      if (bitField0_ != 0) { buildPartial0(result); }
      onBuilt();
      return result;
    }

    private void buildPartialRepeatedFields(Person result) {
      if (((bitField0_ & 0x00000008) != 0)) {
        nums_.makeImmutable();
        bitField0_ = (bitField0_ & ~0x00000008);
      }
      result.nums_ = nums_;
      if (((bitField0_ & 0x00000010) != 0)) {
        integers_.makeImmutable();
        bitField0_ = (bitField0_ & ~0x00000010);
      }
      result.integers_ = integers_;
    }

    private void buildPartial0(Person result) {
      int from_bitField0_ = bitField0_;
      if (((from_bitField0_ & 0x00000001) != 0)) {
        result.id_ = id_;
      }
      if (((from_bitField0_ & 0x00000002) != 0)) {
        result.name_ = name_;
      }
      if (((from_bitField0_ & 0x00000004) != 0)) {
        result.age_ = age_;
      }
      if (((from_bitField0_ & 0x00000020) != 0)) {
        result.timestamp_ = timestamp_;
      }
      if (((from_bitField0_ & 0x00000040) != 0)) {
        result.address_ = addressBuilder_ == null
            ? address_
            : addressBuilder_.build();
      }
    }

    @Override
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof Person) {
        return mergeFrom((Person)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(Person other) {
      if (other == Person.getDefaultInstance()) return this;
      if (other.getId() != 0L) {
        setId(other.getId());
      }
      if (!other.getName().isEmpty()) {
        name_ = other.name_;
        bitField0_ |= 0x00000002;
        onChanged();
      }
      if (other.getAge() != 0) {
        setAge(other.getAge());
      }
      if (!other.nums_.isEmpty()) {
        if (nums_.isEmpty()) {
          nums_ = other.nums_;
          bitField0_ = (bitField0_ & ~0x00000008);
        } else {
          ensureNumsIsMutable();
          nums_.addAll(other.nums_);
        }
        onChanged();
      }
      if (!other.integers_.isEmpty()) {
        if (integers_.isEmpty()) {
          integers_ = other.integers_;
          bitField0_ = (bitField0_ & ~0x00000010);
        } else {
          ensureIntegersIsMutable();
          integers_.addAll(other.integers_);
        }
        onChanged();
      }
      if (other.getTimestamp() != 0L) {
        setTimestamp(other.getTimestamp());
      }
      if (other.hasAddress()) {
        mergeAddress(other.getAddress());
      }
      this.mergeUnknownFields(other.getUnknownFields());
      onChanged();
      return this;
    }

    @Override
    public final boolean isInitialized() {
      return true;
    }

    @Override
    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      if (extensionRegistry == null) {
        throw new NullPointerException();
      }
      try {
        boolean done = false;
        while (!done) {
          int tag = input.readTag();
          switch (tag) {
            case 0:
              done = true;
              break;
            case 8: {
              id_ = input.readUInt64();
              bitField0_ |= 0x00000001;
              break;
            } // case 8
            case 18: {
              name_ = input.readStringRequireUtf8();
              bitField0_ |= 0x00000002;
              break;
            } // case 18
            case 24: {
              age_ = input.readInt32();
              bitField0_ |= 0x00000004;
              break;
            } // case 24
            case 32: {
              int v = input.readInt32();
              ensureNumsIsMutable();
              nums_.addInt(v);
              break;
            } // case 32
            case 34: {
              int length = input.readRawVarint32();
              int limit = input.pushLimit(length);
              ensureNumsIsMutable();
              while (input.getBytesUntilLimit() > 0) {
                nums_.addInt(input.readInt32());
              }
              input.popLimit(limit);
              break;
            } // case 34
            case 40: {
              int v = input.readInt32();
              ensureIntegersIsMutable();
              integers_.addInt(v);
              break;
            } // case 40
            case 42: {
              int length = input.readRawVarint32();
              int limit = input.pushLimit(length);
              ensureIntegersIsMutable();
              while (input.getBytesUntilLimit() > 0) {
                integers_.addInt(input.readInt32());
              }
              input.popLimit(limit);
              break;
            } // case 42
            case 56: {
              timestamp_ = input.readUInt64();
              bitField0_ |= 0x00000020;
              break;
            } // case 56
            case 66: {
              input.readMessage(
                  getAddressFieldBuilder().getBuilder(),
                  extensionRegistry);
              bitField0_ |= 0x00000040;
              break;
            } // case 66
            default: {
              if (!super.parseUnknownField(input, extensionRegistry, tag)) {
                done = true; // was an endgroup tag
              }
              break;
            } // default:
          } // switch (tag)
        } // while (!done)
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        throw e.unwrapIOException();
      } finally {
        onChanged();
      } // finally
      return this;
    }
    private int bitField0_;

    private long id_ ;
    /**
     * <code>uint64 id = 1;</code>
     * @return The id.
     */
    @Override
    public long getId() {
      return id_;
    }
    /**
     * <code>uint64 id = 1;</code>
     * @param value The id to set.
     * @return This builder for chaining.
     */
    public Builder setId(long value) {

      id_ = value;
      bitField0_ |= 0x00000001;
      onChanged();
      return this;
    }
    /**
     * <code>uint64 id = 1;</code>
     * @return This builder for chaining.
     */
    public Builder clearId() {
      bitField0_ = (bitField0_ & ~0x00000001);
      id_ = 0L;
      onChanged();
      return this;
    }

    private Object name_ = "";
    /**
     * <code>string name = 2;</code>
     * @return The name.
     */
    public String getName() {
      Object ref = name_;
      if (!(ref instanceof String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        String s = bs.toStringUtf8();
        name_ = s;
        return s;
      } else {
        return (String) ref;
      }
    }
    /**
     * <code>string name = 2;</code>
     * @return The bytes for name.
     */
    public com.google.protobuf.ByteString
        getNameBytes() {
      Object ref = name_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (String) ref);
        name_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string name = 2;</code>
     * @param value The name to set.
     * @return This builder for chaining.
     */
    public Builder setName(
        String value) {
      if (value == null) { throw new NullPointerException(); }
      name_ = value;
      bitField0_ |= 0x00000002;
      onChanged();
      return this;
    }
    /**
     * <code>string name = 2;</code>
     * @return This builder for chaining.
     */
    public Builder clearName() {
      name_ = getDefaultInstance().getName();
      bitField0_ = (bitField0_ & ~0x00000002);
      onChanged();
      return this;
    }
    /**
     * <code>string name = 2;</code>
     * @param value The bytes for name to set.
     * @return This builder for chaining.
     */
    public Builder setNameBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) { throw new NullPointerException(); }
      checkByteStringIsUtf8(value);
      name_ = value;
      bitField0_ |= 0x00000002;
      onChanged();
      return this;
    }

    private int age_ ;
    /**
     * <code>int32 age = 3;</code>
     * @return The age.
     */
    @Override
    public int getAge() {
      return age_;
    }
    /**
     * <code>int32 age = 3;</code>
     * @param value The age to set.
     * @return This builder for chaining.
     */
    public Builder setAge(int value) {

      age_ = value;
      bitField0_ |= 0x00000004;
      onChanged();
      return this;
    }
    /**
     * <code>int32 age = 3;</code>
     * @return This builder for chaining.
     */
    public Builder clearAge() {
      bitField0_ = (bitField0_ & ~0x00000004);
      age_ = 0;
      onChanged();
      return this;
    }

    private com.google.protobuf.Internal.IntList nums_ = emptyIntList();
    private void ensureNumsIsMutable() {
      if (!((bitField0_ & 0x00000008) != 0)) {
        nums_ = mutableCopy(nums_);
        bitField0_ |= 0x00000008;
      }
    }
    /**
     * <code>repeated int32 nums = 4;</code>
     * @return A list containing the nums.
     */
    public java.util.List<Integer>
        getNumsList() {
      return ((bitField0_ & 0x00000008) != 0) ?
               java.util.Collections.unmodifiableList(nums_) : nums_;
    }
    /**
     * <code>repeated int32 nums = 4;</code>
     * @return The count of nums.
     */
    public int getNumsCount() {
      return nums_.size();
    }
    /**
     * <code>repeated int32 nums = 4;</code>
     * @param index The index of the element to return.
     * @return The nums at the given index.
     */
    public int getNums(int index) {
      return nums_.getInt(index);
    }
    /**
     * <code>repeated int32 nums = 4;</code>
     * @param index The index to set the value at.
     * @param value The nums to set.
     * @return This builder for chaining.
     */
    public Builder setNums(
        int index, int value) {

      ensureNumsIsMutable();
      nums_.setInt(index, value);
      onChanged();
      return this;
    }
    /**
     * <code>repeated int32 nums = 4;</code>
     * @param value The nums to add.
     * @return This builder for chaining.
     */
    public Builder addNums(int value) {

      ensureNumsIsMutable();
      nums_.addInt(value);
      onChanged();
      return this;
    }
    /**
     * <code>repeated int32 nums = 4;</code>
     * @param values The nums to add.
     * @return This builder for chaining.
     */
    public Builder addAllNums(
        Iterable<? extends Integer> values) {
      ensureNumsIsMutable();
      com.google.protobuf.AbstractMessageLite.Builder.addAll(
          values, nums_);
      onChanged();
      return this;
    }
    /**
     * <code>repeated int32 nums = 4;</code>
     * @return This builder for chaining.
     */
    public Builder clearNums() {
      nums_ = emptyIntList();
      bitField0_ = (bitField0_ & ~0x00000008);
      onChanged();
      return this;
    }

    private com.google.protobuf.Internal.IntList integers_ = emptyIntList();
    private void ensureIntegersIsMutable() {
      if (!((bitField0_ & 0x00000010) != 0)) {
        integers_ = mutableCopy(integers_);
        bitField0_ |= 0x00000010;
      }
    }
    /**
     * <code>repeated int32 integers = 5;</code>
     * @return A list containing the integers.
     */
    public java.util.List<Integer>
        getIntegersList() {
      return ((bitField0_ & 0x00000010) != 0) ?
               java.util.Collections.unmodifiableList(integers_) : integers_;
    }
    /**
     * <code>repeated int32 integers = 5;</code>
     * @return The count of integers.
     */
    public int getIntegersCount() {
      return integers_.size();
    }
    /**
     * <code>repeated int32 integers = 5;</code>
     * @param index The index of the element to return.
     * @return The integers at the given index.
     */
    public int getIntegers(int index) {
      return integers_.getInt(index);
    }
    /**
     * <code>repeated int32 integers = 5;</code>
     * @param index The index to set the value at.
     * @param value The integers to set.
     * @return This builder for chaining.
     */
    public Builder setIntegers(
        int index, int value) {

      ensureIntegersIsMutable();
      integers_.setInt(index, value);
      onChanged();
      return this;
    }
    /**
     * <code>repeated int32 integers = 5;</code>
     * @param value The integers to add.
     * @return This builder for chaining.
     */
    public Builder addIntegers(int value) {

      ensureIntegersIsMutable();
      integers_.addInt(value);
      onChanged();
      return this;
    }
    /**
     * <code>repeated int32 integers = 5;</code>
     * @param values The integers to add.
     * @return This builder for chaining.
     */
    public Builder addAllIntegers(
        Iterable<? extends Integer> values) {
      ensureIntegersIsMutable();
      com.google.protobuf.AbstractMessageLite.Builder.addAll(
          values, integers_);
      onChanged();
      return this;
    }
    /**
     * <code>repeated int32 integers = 5;</code>
     * @return This builder for chaining.
     */
    public Builder clearIntegers() {
      integers_ = emptyIntList();
      bitField0_ = (bitField0_ & ~0x00000010);
      onChanged();
      return this;
    }

    private long timestamp_ ;
    /**
     * <code>uint64 timestamp = 7;</code>
     * @return The timestamp.
     */
    @Override
    public long getTimestamp() {
      return timestamp_;
    }
    /**
     * <code>uint64 timestamp = 7;</code>
     * @param value The timestamp to set.
     * @return This builder for chaining.
     */
    public Builder setTimestamp(long value) {

      timestamp_ = value;
      bitField0_ |= 0x00000020;
      onChanged();
      return this;
    }
    /**
     * <code>uint64 timestamp = 7;</code>
     * @return This builder for chaining.
     */
    public Builder clearTimestamp() {
      bitField0_ = (bitField0_ & ~0x00000020);
      timestamp_ = 0L;
      onChanged();
      return this;
    }

    private Address address_;
    private com.google.protobuf.SingleFieldBuilderV3<
        Address, Address.Builder, AddressOrBuilder> addressBuilder_;
    /**
     * <code>.Address address = 8;</code>
     * @return Whether the address field is set.
     */
    public boolean hasAddress() {
      return ((bitField0_ & 0x00000040) != 0);
    }
    /**
     * <code>.Address address = 8;</code>
     * @return The address.
     */
    public Address getAddress() {
      if (addressBuilder_ == null) {
        return address_ == null ? Address.getDefaultInstance() : address_;
      } else {
        return addressBuilder_.getMessage();
      }
    }
    /**
     * <code>.Address address = 8;</code>
     */
    public Builder setAddress(Address value) {
      if (addressBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        address_ = value;
      } else {
        addressBuilder_.setMessage(value);
      }
      bitField0_ |= 0x00000040;
      onChanged();
      return this;
    }
    /**
     * <code>.Address address = 8;</code>
     */
    public Builder setAddress(
        Address.Builder builderForValue) {
      if (addressBuilder_ == null) {
        address_ = builderForValue.build();
      } else {
        addressBuilder_.setMessage(builderForValue.build());
      }
      bitField0_ |= 0x00000040;
      onChanged();
      return this;
    }
    /**
     * <code>.Address address = 8;</code>
     */
    public Builder mergeAddress(Address value) {
      if (addressBuilder_ == null) {
        if (((bitField0_ & 0x00000040) != 0) &&
          address_ != null &&
          address_ != Address.getDefaultInstance()) {
          getAddressBuilder().mergeFrom(value);
        } else {
          address_ = value;
        }
      } else {
        addressBuilder_.mergeFrom(value);
      }
      bitField0_ |= 0x00000040;
      onChanged();
      return this;
    }
    /**
     * <code>.Address address = 8;</code>
     */
    public Builder clearAddress() {
      bitField0_ = (bitField0_ & ~0x00000040);
      address_ = null;
      if (addressBuilder_ != null) {
        addressBuilder_.dispose();
        addressBuilder_ = null;
      }
      onChanged();
      return this;
    }
    /**
     * <code>.Address address = 8;</code>
     */
    public Address.Builder getAddressBuilder() {
      bitField0_ |= 0x00000040;
      onChanged();
      return getAddressFieldBuilder().getBuilder();
    }
    /**
     * <code>.Address address = 8;</code>
     */
    public AddressOrBuilder getAddressOrBuilder() {
      if (addressBuilder_ != null) {
        return addressBuilder_.getMessageOrBuilder();
      } else {
        return address_ == null ?
            Address.getDefaultInstance() : address_;
      }
    }
    /**
     * <code>.Address address = 8;</code>
     */
    private com.google.protobuf.SingleFieldBuilderV3<
        Address, Address.Builder, AddressOrBuilder> 
        getAddressFieldBuilder() {
      if (addressBuilder_ == null) {
        addressBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<
            Address, Address.Builder, AddressOrBuilder>(
                getAddress(),
                getParentForChildren(),
                isClean());
        address_ = null;
      }
      return addressBuilder_;
    }
    @Override
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFields(unknownFields);
    }

    @Override
    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }


    // @@protoc_insertion_point(builder_scope:Person)
  }

  // @@protoc_insertion_point(class_scope:Person)
  private static final Person DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new Person();
  }

  public static Person getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<Person>
      PARSER = new com.google.protobuf.AbstractParser<Person>() {
    @Override
    public Person parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      Builder builder = newBuilder();
      try {
        builder.mergeFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        throw e.setUnfinishedMessage(builder.buildPartial());
      } catch (com.google.protobuf.UninitializedMessageException e) {
        throw e.asInvalidProtocolBufferException().setUnfinishedMessage(builder.buildPartial());
      } catch (java.io.IOException e) {
        throw new com.google.protobuf.InvalidProtocolBufferException(e)
            .setUnfinishedMessage(builder.buildPartial());
      }
      return builder.buildPartial();
    }
  };

  public static com.google.protobuf.Parser<Person> parser() {
    return PARSER;
  }

  @Override
  public com.google.protobuf.Parser<Person> getParserForType() {
    return PARSER;
  }

  @Override
  public Person getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}
